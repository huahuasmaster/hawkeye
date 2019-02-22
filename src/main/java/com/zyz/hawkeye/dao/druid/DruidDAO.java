package com.zyz.hawkeye.dao.druid;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.util.OkHttpClientWrapper;
import in.zapr.druid.druidry.query.aggregation.DruidAggregationQuery;
import in.zapr.druid.druidry.query.aggregation.DruidTimeSeriesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Repository
/**
 * 使用http访问，操作Druid，官方api文档地址：http://druid.io/docs/latest/operations/api-reference.html
 */
public class DruidDAO {

    @Value("${druid.host}")
    private String druidHost;

    @Value("${druid.queryport}")
    private String port;

    private String prefix;

    @PostConstruct
    public void init() {
        if (StringUtils.isEmpty(druidHost) || StringUtils.isEmpty(port)) {
            throw new HawkEyeException("DruidDao必要参数缺失，初始化失败");
        }
        this.prefix = druidHost + ":" + port;
    }

    @Autowired
    private OkHttpClientWrapper clientWrapper;

    /**
     * 查询所有的datasource名称
     *
     * @return
     */
    public String listAllDatasourceName() {
        return clientWrapper.get(prefix + "/druid/coordinator/v1/datasources/");
    }

    /**
     * 查询所有的datasource的详细信息
     *
     * @return
     */
    public String listAllDatasource() {
        return clientWrapper.get(prefix + "/druid/coordinator/v1/datasources?full");
    }

    /**
     * 执行查询
     *
     * @param query 查询条件，格式为JSON
     * @return JSON格式的返回结果
     */
    public String query(String query) {
        return clientWrapper.post(prefix + "/druid/v2", query);
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
        String result = query(queryJsonStr);
        return result;
    }
}
