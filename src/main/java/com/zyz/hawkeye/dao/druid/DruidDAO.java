package com.zyz.hawkeye.dao.druid;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryParams;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.util.OkHttpClientWrapper;
import in.zapr.druid.druidry.query.aggregation.DruidAggregationQuery;
import in.zapr.druid.druidry.query.aggregation.DruidTimeSeriesQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;


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
        return clientWrapper.post(brokerPrefix + "/druid/v2", query);
    }

    public String query (DruidQueryParams params) {
        DruidAggregationQuery query = DruidTimeSeriesQuery.builder()
                .aggregators(params.getAggregations())
                .granularity(params.getGranularity())
                .filter(params.getFilter())
                .intervals(params.getIntervals())
                .dataSource(params.getDataSource())
                .postAggregators(params.getPostAggregations())
                .context(params.getContext())
                .build();
        String queryJsonStr = JSON.toJSONString(query);
        return query(queryJsonStr);
    }

    public String updateDatasource(DruidDataSource dataSource) {
        log.info("开始更新数据源 数据源名称={} datasource={}", dataSource.getDataSchema().getDataSource(), JSON.toJSONString(dataSource));
        return clientWrapper.post(overloadPrefix + "/druid/indexer/v1/supervisor/", JSON.toJSONString(dataSource));
    }
}
