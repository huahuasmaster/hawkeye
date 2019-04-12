package com.zyz.hawkeye.config;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.http.BuryInfo;
import com.zyz.hawkeye.http.DataSourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class KafkaDruidDataSourceTemplate {
    public static DruidDataSource newInstance(DataSourceVO dataSourceVO) {
        // ParseSpecBean
        DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean parserSpecBean = new DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean();
        parserSpecBean.setFormat("json");
        parserSpecBean.setTimestampSpec(new DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean.TimestampSpecBean("_time", "posix"));// 默认是秒为单位的时间戳
        parserSpecBean.setDimensionsSpec((new DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean.DimensionsSpecBean(
                dataSourceVO.getDimensionList(),
                null
        )));


        // MetricsSpecBean
        List<DruidDataSource.DataSchemaBean.MetricsSpecBean> metricsSpecBeans = new ArrayList<>();

        // 默认添加count
        metricsSpecBeans.add(
                new DruidDataSource.DataSchemaBean.MetricsSpecBean("count", "count")
        );

        if (!CollectionUtils.isEmpty(dataSourceVO.getMetricList())) {
            dataSourceVO.getMetricList().forEach((metric) -> metricsSpecBeans.add(new DruidDataSource.DataSchemaBean.MetricsSpecBean("doubleSum", metric, metric)));
        }


        // DataSchemaBean
        DruidDataSource.DataSchemaBean dataSchemaBean = DruidDataSource.DataSchemaBean.builder()
                .dataSource(getTopic(dataSourceVO))
                .granularitySpec(
                        DruidDataSource.DataSchemaBean.GranularitySpecBean.builder()
                                .queryGranularity(dataSourceVO.getQueryGranularity())
                                .segmentGranularity("DAY")
                                .rollup(dataSourceVO.getRollUp())
                                .type("uniform")
                                .build()
                )
                .parser(
                        DruidDataSource.DataSchemaBean.ParserBean.builder()
                                .type("string")
                                .parseSpec(parserSpecBean)
                                .build()
                )
                .metricsSpec(metricsSpecBeans)
                .build();

        // IoConfigBean
        DruidDataSource.IoConfigBean ioConfigBean = DruidDataSource.IoConfigBean.builder()
                .topic(getTopic(dataSourceVO))
                .consumerProperties(new DruidDataSource.IoConfigBean.ConsumerPropertiesBean("192.168.1.101:9092"))
                .taskCount(1)
                .replicas(1)
                .build();

        //
        DruidDataSource.TuningConfigBean tuningConfigBean = new DruidDataSource.TuningConfigBean();
        tuningConfigBean.setType("kafka");
        tuningConfigBean.setMaxRowsPerSegment(5000000);

        return DruidDataSource.builder()
                .dataSchema(dataSchemaBean)
                .ioConfig(ioConfigBean)
                .type("kafka")
                .tuningConfig(tuningConfigBean)
                .context(null)
                .build();
    }

    private static String getTopic(DataSourceVO dataSourceVO) {
        String suffix;
        switch (DataSourceType.fromType(dataSourceVO.getType())) {
            case MYSQL: suffix = JSON.parseObject(dataSourceVO.getSourceInfo(), MysqlInfo.class).getTable();break;
            case BURY: suffix = JSON.parseObject(dataSourceVO.getSourceInfo(), BuryInfo.class).getEvent();break;
            default: suffix = "";
        }
        return "hawkeye_" + dataSourceVO.getType().toLowerCase() + "_" + suffix;
    }
}
