package com.zyz.hawkeye.service;

import ch.qos.logback.core.util.AggregationType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyz.hawkeye.dao.ChartRepository;
import com.zyz.hawkeye.dao.DatasourceRepository;
import com.zyz.hawkeye.dao.druid.DruidDAO;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryParams;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryResult;
import com.zyz.hawkeye.dao.entity.ChartEntity;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.enums.metric.GranularityOptions;
import com.zyz.hawkeye.enums.metric.MetricAggregationType;
import com.zyz.hawkeye.enums.metric.MetricVariableDataType;
import com.zyz.hawkeye.enums.metric.MetricVariableType;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.metric.*;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.dimension.DefaultDimension;
import in.zapr.druid.druidry.dimension.enums.OutputType;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private DruidDAO druidDAO;

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private DatasourceRepository datasourceRepository;

    public int save(ChartVO chartVO) {
        return chartRepository.save(VO2Entity(chartVO)).getId();
    }

    public ChartVO get(Integer id) {
        return entity2VO(chartRepository.findById(id).orElse(null));
    }

    public List<ChartVO> listByDashboardId(Integer dashboardId) {
        List<ChartVO> result = new ArrayList<>();
        List<ChartEntity> entities = chartRepository.findAll();
        if (CollectionUtils.isEmpty(entities)) {
            return result;
//            throw new HawkEyeException("该看板下没有表格");
        }
        entities.forEach(entity -> result.add(entity2VO(entity)));
        return result;
    }

    public MetricResultVO getMetricResultByChart(MetricParamChartVO metricParamChartVO) {

        // 1查询并检验图表
        ChartEntity chartEntity = chartRepository.findById(metricParamChartVO.getChartId())
                .orElseThrow(() -> new HawkEyeException("图表不存在或者已经被删除"));


        // 2根据图表配置生成查询参数
        DruidQueryParams params = new DruidQueryParams();
        List<Interval> intervals = metricParamChartVO.getIntervals().stream()
                .map(i -> new Interval(new DateTime(i.getStartTime()), new DateTime(i.getEndTime())))
                .collect(Collectors.toList());
        params.setIntervals(intervals);

        params.setGranularity(GranularityOptions.fromType(
                StringUtils.isEmpty(metricParamChartVO.getPeriod()) ? chartEntity.getDefaultGranularity() : metricParamChartVO.getPeriod()
        ).getGranularity());

        // 产生聚合器char
        List<MetricQueryParamRawVO.Aggregation> aggregations = JSON.parseArray(chartEntity.getAggregations(), MetricQueryParamRawVO.Aggregation.class);
        List<DruidAggregator> finalAggregators = new ArrayList<>();
        aggregations.forEach((aggregation -> {
            // 此处的Variable是虚构的
            MetricVariableVO.Variable variable = new MetricVariableVO.Variable();
            variable.setQueryName(aggregation.getMetric()); // 在此处是用于查询的字段名,另一个才是查询出的结果的别名
            variable.setVariableType(MetricVariableType.METRIC);
            variable.setDataType(MetricVariableDataType.DOUBLE);
            finalAggregators.add(
                    aggregation.getMetricAggregationType().getAggregation(variable, aggregation.getAlias())
            );
        }));
        params.setAggregations(finalAggregators);
//        if (metricParamChartVO.getChartId() == 1) {
//            params.setAggregations(
//                    Collections.singletonList(MetricAggregationType.COUNT.getAggregation(null, "count"))
//            );
//        } else {
//            MetricVariableVO.Variable v = new MetricVariableVO.Variable();
//            v.setQueryName("amount_sum");// 在此处是用于查询的字段名,另一个才是查询出的结果的别名
//            v.setDataType(MetricVariableDataType.DOUBLE);
//            params.setAggregations(
//                    Collections.singletonList(MetricAggregationType.SUM.getAggregation(v, "amount_sum"))
//            );
//        }


        // 填入维度
        List<MetricQueryParamRawVO.Dimension> dimensionList = JSON.parseArray(chartEntity.getDimensions(), MetricQueryParamRawVO.Dimension.class);
        if (!CollectionUtils.isEmpty(dimensionList)) {
            List<DefaultDimension> collect = dimensionList.stream()
                    .map(dimension -> DefaultDimension.builder().dimension(dimension.getDimensionField())
                    .outputName(dimension.getDimensionName(dimension.getDimensionField()))
                    .outputType(OutputType.STRING)
                    .build())
                    .collect(Collectors.toList());
            params.setDimensionSpecs(collect);
        }

        // 阈值
        Integer threshlod = chartEntity.getThreshold();
        params.setThreshold(threshlod == null || threshlod <= 0 ? 200 : threshlod);

        List<DruidQueryResult> results;

        if (chartEntity.getType().equals("funnel")) {
            // 获取指定的数据源们
            JSONObject config = JSON.parseObject(chartEntity.getConfig());
            List<Integer> datasourceIds = JSON.parseArray(config.getString("datasourceIds"), Integer.class);
            // 需要聚合多个数据源的数据,并确保其全部有效
            List<DatasourceEntity> datasourceEntities = datasourceIds.stream()
                    .map(id -> datasourceRepository.findById(id))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(DatasourceEntity::isEnable)
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(datasourceEntities)) {
                throw new HawkEyeException("无有效数据源");
            }

            // 逐个查询结果, 并组装成数据源描述-》查询结果的映射
            params.setAggregations(
                    Collections.singletonList(MetricAggregationType.COUNT.getAggregation(null, "数量"))
            );

            Map<String, List<DruidQueryResult>> nameResultMap = datasourceEntities.stream()
                    .collect(Collectors.toMap(
                            DatasourceEntity::getSourceDesc,
                            entity -> {
                                params.setDataSource(entity.getName());
                                return druidDAO.query(params);
                            }
                    ));

            // 将结果进行聚合，成为一个普通的druidresult
            DruidQueryResult finalQueryResult = null;
            List<Map<String, Object>> finalResult = new ArrayList<>();

            for (Map.Entry<String, List<DruidQueryResult>> entry : nameResultMap.entrySet()) {
                String datasourceName = entry.getKey();
                List<DruidQueryResult> result = entry.getValue();

                if (result.size() > 0) {
                    if (finalQueryResult == null) {
                        finalQueryResult = result.get(0);
                    }

                    // 提取出唯一有价值的数据
                    Optional<Map.Entry<String, Object>> e = Optional.ofNullable(result.get(0))
                            .map(DruidQueryResult::getResult)
                            .map(list -> list.get(0))
                            .map(Map::entrySet)
                            .map(Set::iterator)
                            .filter(Iterator::hasNext)
                            .map(Iterator::next);

                    if (e.isPresent()) {
                        Map<String, Object> dataOfAnDatasource = new HashMap<>();
                        dataOfAnDatasource.put(e.get().getKey(), e.get().getValue()); // 将作为图表的指标
                        dataOfAnDatasource.put("名称", datasourceName); //将作为图标的维度
                        finalResult.add(dataOfAnDatasource);
                    }
                }
            }

            if (finalQueryResult == null) {
                finalQueryResult = new DruidQueryResult();
                finalQueryResult.setTimestamp(new DateTime(metricParamChartVO.getIntervals().get(0).getStartTime()).toString());
            }

            finalQueryResult.setResult(finalResult);

            results = Collections.singletonList(finalQueryResult);

        } else {

            // 数据源名称
            DatasourceEntity datasourceEntity = datasourceRepository.findById(chartEntity.getDatasourceId())
                    .orElseThrow(() -> new HawkEyeException("数据源不存在或者已经被关闭"));
            params.setDataSource(datasourceEntity.getName());

            // 3查询得到结果
            results = druidDAO.query(params);
        }

        // 4转换结果为VO
        MetricResultVO resultVO = new MetricResultVO();
        List<MetricResultVO.Result> resultVOs = results.stream()
                .map(r -> new MetricResultVO.Result(r.getTimestamp().getMillis(), r.getResult()))
                .collect(Collectors.toList());
        MetricQueryParamRawVO.Interval interval = new MetricQueryParamRawVO.Interval();
        interval.setStartTime(metricParamChartVO.getIntervals().get(0).getStartTime());
        interval.setEndTime(metricParamChartVO.getIntervals().get(0).getEndTime());
        resultVO.setMetricList(makeUp(resultVOs, GranularityOptions.fromType(metricParamChartVO.getPeriod()), Collections.singletonList(interval)));
        return resultVO;

    }



    private List<MetricResultVO.Result> makeUp(
            List<MetricResultVO.Result> results,
            GranularityOptions granularity,
            List<MetricQueryParamRawVO.Interval> intervalList) {
        List<MetricResultVO.Result> makeUpList = new ArrayList<>();
        MetricQueryParamRawVO.Interval interval = intervalList.get(0);

        if (GranularityOptions.ALL.equals(granularity)) {
            if (CollectionUtils.isEmpty(results)) {
                makeUpList.add(new MetricResultVO.Result(interval.getStartTime(), new ArrayList<>()));
                return makeUpList;
            }
            return results;
        } else {
            results = results.stream().sorted((r1, r2) -> r1.getTimestamp() < r2.getTimestamp() ? -1 : 1).collect(Collectors.toList());
            long intervalCount = (interval.getEndTime() - interval.getStartTime()) / granularity.getIntervalMillisecond();
            AtomicLong timeBefore = new AtomicLong(interval.getStartTime());
            while (intervalCount >= 0) {
                long timeNext = timeBefore.get() + granularity.getIntervalMillisecond();
                List<List<Map<String, Object>>> collect = results.stream()
                        .filter(r -> r.getTimestamp() >= timeBefore.get() && r.getTimestamp() < timeNext)
                        .map(MetricResultVO.Result::getData)
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    collect.forEach(c -> makeUpList.add(new MetricResultVO.Result(timeBefore.get(), c)));
                } else {
                    makeUpList.add(new MetricResultVO.Result(timeBefore.get(), new ArrayList<>()));
                }
                intervalCount--;
                timeBefore.set(timeNext);
            }
            return makeUpList.stream().filter(m -> m.getTimestamp() != interval.getEndTime()).collect(Collectors.toList());
        }

    }

    private ChartVO entity2VO(ChartEntity chartEntity) {
        if (chartEntity == null) {
            return null;
        }
        ChartVO chartVO = new ChartVO();
        BeanUtils.copyProperties(chartEntity, chartVO);
        chartVO.setAggregations(JSON.parseArray(chartEntity.getAggregations(), MetricQueryParamRawVO.Aggregation.class));
        chartVO.setDimensions(JSON.parseArray(chartEntity.getDimensions(), MetricQueryParamRawVO.Dimension.class));
        chartVO.setFilters(JSON.parseArray(chartEntity.getFilters(), MetricQueryParamRawVO.Filter.class));
        chartVO.setConfig(JSON.parse(chartEntity.getConfig()));
        chartVO.setDesc(chartEntity.getChartDesc());
        return chartVO;
    }

    private ChartEntity VO2Entity(ChartVO chartVO) {
        if (chartVO == null) {
            return null;
        }
        ChartEntity chartEntity = new ChartEntity();
//        chartEntity.setId(chartVO.getId());
        chartEntity.setName(chartVO.getName());
        chartEntity.setChartDesc(chartVO.getDesc());
        chartEntity.setDatasourceId(chartVO.getDatasourceId());
        chartEntity.setType(chartVO.getType());
        chartEntity.setConfig(JSON.toJSONString(chartVO.getConfig()));
        chartEntity.setAggregations(JSON.toJSONString(chartVO.getAggregations()));
        chartEntity.setDimensions(JSON.toJSONString(chartVO.getDimensions()));
        chartEntity.setFilters(JSON.toJSONString(chartVO.getFilters()));
        chartEntity.setThreshold(chartVO.getThreshold());
        return chartEntity;
    }

}
