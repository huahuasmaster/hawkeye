package com.zyz.hawkeye.dao.druid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.zyz.hawkeye.dao.druid.entity.*;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.util.OkHttpClientWrapper;
import in.zapr.druid.druidry.query.aggregation.DruidAggregationQuery;
import in.zapr.druid.druidry.query.aggregation.DruidGroupByQuery;
import in.zapr.druid.druidry.query.aggregation.DruidTimeSeriesQuery;
import in.zapr.druid.druidry.query.aggregation.DruidTopNQuery;
import in.zapr.druid.druidry.topNMetric.SimpleMetric;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 使用http访问，操作Druid，官方api文档地址：http://druid.io/docs/latest/operations/api-reference.html
 */
@Repository
@Slf4j
public class DruidDAO {

    @Value("${druid.host}")
    private String druidHost;

    @Value("${druid.broker}")
    private String broker;

    @Value("${druid.overload}")
    private String overload;

    @Value("{druid.coordinator}")
    private String coordinator;

    private String coordinatorPrefix;

    private String brokerPrefix;

    private String overloadPrefix;

    public static final String METRIC_COLUMN_TIMESTAMP = "_time";

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @PostConstruct
    public void init() {
        if (StringUtils.isEmpty(druidHost) || StringUtils.isEmpty(broker) || StringUtils.isEmpty(overload)) {
            throw new HawkEyeException("DruidDao必要参数缺失，初始化失败");
        }
        this.brokerPrefix = druidHost + ":" + broker;
        this.overloadPrefix = druidHost + ":" + overload;
        this.coordinatorPrefix = druidHost + ":" + coordinator;
    }

    @Autowired
    private OkHttpClientWrapper clientWrapper;

    /**
     * 查询所有的datasource名称
     *
     * @return
     */
    public String listAllDatasourceName() {
        return clientWrapper.get(coordinatorPrefix + "/druid/coordinator/v1/datasources/");
    }

    public String getDatasource(String datasource) {
        return clientWrapper.get(coordinatorPrefix + "/druid/coordinator/v1/datasources/" + datasource);
    }

    /**
     * 执行查询
     *
     * @param query 查询条件，格式为JSON
     * @return JSON格式的返回结果
     */
    public String query(String query) {
        log.info("开始查询，json={}", query);
        return clientWrapper.post(brokerPrefix + "/druid/v2", query);
    }


    public String updateDatasource(DruidDataSource dataSource) {
        log.info("开始更新数据源 数据源名称={} datasource={}", dataSource.getDataSchema().getDataSource(), JSON.toJSONString(dataSource));
        return clientWrapper.post(overloadPrefix + "/druid/indexer/v1/supervisor/", JSON.toJSONString(dataSource));
    }

    public void insert(String dataSourceName, Map<String, Object> data) {
        if (data == null) {
            throw new HawkEyeException("druid数据不能为空");
        }
        data.entrySet().forEach(entry -> {
            if (entry.getValue() instanceof BigDecimal) {
                entry.setValue(((BigDecimal) entry.getValue()).doubleValue());
            }
        });
        // 如果调用方没有写入时间，则默认为当前时间
        data.computeIfAbsent(METRIC_COLUMN_TIMESTAMP, (key) -> System.currentTimeMillis());
//        ProducerRecord<String, String> record = new ProducerRecord<>(dataSourceName, JSON.toJSONString(data));
        // [约定]datasourcename同时也是kafka的topic
        kafkaTemplate.send(dataSourceName, JSON.toJSONString(data));
    }

    /**
     * 查询一段时间内指标.
     *
     * @param param
     */
    public List<DruidQueryResult> query(DruidQueryParams param) {

        DruidAggregationQuery query;
        if (CollectionUtils.isEmpty(param.getDimensionSpecs()) && Objects.isNull(param.getHaving())) {
            query = DruidTimeSeriesQuery.builder()
                    .aggregators(param.getAggregations())
                    .granularity(param.getGranularity())
                    .filter(param.getFilter())
                    .intervals(param.getIntervals())
                    .dataSource(param.getDataSource())
                    .postAggregators(param.getPostAggregations())
                    .context(param.getContext())
                    .build();
        } else if (param.getDimensionSpecs().size() == 1 && Objects.isNull(param.getHaving())) {
            query = DruidTopNQuery.builder()
                    .aggregators(param.getAggregations())
                    .granularity(param.getGranularity())
                    .filter(param.getFilter())
                    .intervals(param.getIntervals())
                    .dimension(param.getDimensionSpecs().get(0))
                    .dataSource(param.getDataSource())
                    .threshold(param.getThreshold())
                    .topNMetric(new SimpleMetric(param.getAggregations().get(0).getName()))
                    .postAggregators(param.getPostAggregations())
                    .context(param.getContext())
                    .build();
        } else {
            query = DruidGroupByQuery.builder()
                    .aggregators(param.getAggregations())
                    .granularity(param.getGranularity())
                    .filter(param.getFilter())
                    .intervals(param.getIntervals())
                    .dataSource(param.getDataSource())
                    .postAggregators(param.getPostAggregations())
                    .dimensions(new ArrayList<>(param.getDimensionSpecs()))
                    .context(param.getContext())
                    .build();
        }


        ObjectMapper mapper = new ObjectMapper();
        List<DruidQueryResult> results;
        try {
            String requiredJson = mapper.writeValueAsString(query);
            // 添加Having条件，Druidry 客户端暂时不支持
            if (Objects.nonNull(param.getHaving())) {
                JSONObject jsonObject = JSON.parseObject(requiredJson);
                jsonObject.put("having", param.getHaving());
                requiredJson = jsonObject.toJSONString();
            }
            String response = query(requiredJson);
            log.info("[Druid数据查询]：当前查询语句：{}，Druid返回信息：{}", requiredJson, response);
            // timeSeries返回的结构体转换
            if (query instanceof DruidTimeSeriesQuery) {
                List<DruidQueryTimeSeriesResult> druidQueryTimeSeriesResults = JSON.parseArray(response, DruidQueryTimeSeriesResult.class);
                results = druidQueryTimeSeriesResults.stream()
                        .map(r -> new DruidQueryResult(r.getTimestamp(), Lists.newArrayList(r.getResult()))).collect(Collectors.toList());

            } else if (query instanceof DruidGroupByQuery) {
                // groupBy返回的结构体转换，groupBy返回的event都是独立的，需要做时间聚合
                List<DruidQueryGroupByResult> druidQueryGroupByResults = JSON.parseArray(response, DruidQueryGroupByResult.class);
                HashMap<DateTime, List<Map<String, Object>>> map = new HashMap<>();
                druidQueryGroupByResults.forEach(result -> {
                    List<Map<String, Object>> maps = map.get(result.getTimestamp());
                    if (!CollectionUtils.isEmpty(maps)) {
                        maps.add(result.getEvent());
                    } else {
                        map.put(result.getTimestamp(), Lists.newArrayList(result.getEvent()));
                    }
                });
                results = map.entrySet().stream().map(e -> new DruidQueryResult(e.getKey(), e.getValue())).collect(Collectors.toList());
            } else {
                results = JSON.parseArray(response, DruidQueryResult.class);
            }
            results = filter(results);
            return results;
        } catch (IOException e) {
            throw new HawkEyeException("[Druid查询异常]", e);
        }
    }

    /**
     * 去除返回的[] 、 -9223372036854776000、-Infinity
     * 小数点保留4位小数
     *
     * @param druidQueryResultList
     * @return
     */
    private List<DruidQueryResult> filter(List<DruidQueryResult> druidQueryResultList) {
        return druidQueryResultList.stream()
                .filter(Objects::nonNull)
                .filter(r -> !CollectionUtils.isEmpty(r.getResult()))
                .filter(r -> {
                    r.getResult().forEach(m -> {
                        // 获取map中值为-9223372036854776000的key
                        Set<String> collect = m.entrySet().stream()
                                .filter(e -> (e.getValue() instanceof Long && (Long) e.getValue() <= Long.MIN_VALUE) || (e.getValue() instanceof String && (e.getValue()).equals("-Infinity")))
                                .map(Map.Entry::getKey).collect(Collectors.toSet());
                        // 移除map中值为-9223372036854776000的key
                        collect.forEach(m::remove);
                        // 小数点保留4位小数
                        m.entrySet().stream().forEach(e -> {
                            if (e.getValue() instanceof BigDecimal) {
                                e.setValue(((BigDecimal) e.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                            }
                        });
                    });
                    // 过滤list中只有一个map且map的size为0的元素
                    return !(r.getResult().size() == 1 && r.getResult().get(0).size() == 0);
                }).collect(Collectors.toList());
    }
}
