package com.zyz.hawkeye.dao.druid;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryParams;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryResult;
import com.zyz.hawkeye.enums.metric.GranularityOptions;
import com.zyz.hawkeye.enums.metric.MetricAggregationType;
import com.zyz.hawkeye.http.metric.MetricQueryParamRawVO;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.aggregator.CountAggregator;
import in.zapr.druid.druidry.extensions.distinctcount.aggregator.DistinctCountAggregator;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DruidDAOTest {

    @Autowired
    private DruidDAO druidDAO;

    @Test
    public void listAllDatasource() {
        log.info(druidDAO.listAllDatasourceName());
    }

    @Test
    public void queryTest() {
        DruidQueryParams params = new DruidQueryParams();
        params.setDataSource("hawkeye_mysql_t_order");
        DateTime dateTime = new DateTime();
        params.setIntervals(Collections.singletonList(new Interval(dateTime.plusDays(-1), dateTime)));
        params.setGranularity(GranularityOptions.fromType("1天").getGranularity());
        params.setAggregations(
                Collections.singletonList(new CountAggregator("count"))
        );
        List<DruidQueryResult> result = druidDAO.query(params);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 1);
        log.info(JSON.toJSONString(result.get(0)));
    }

    @Test
    public void updateSourceTest() {


        // ParseSpecBean
        DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean parserSpecBean = new DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean();
        parserSpecBean.setFormat("json");
        parserSpecBean.setTimestampSpec(new DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean.TimestampSpecBean("druidDate", "auto"));
        parserSpecBean.setDimensionsSpec(new DruidDataSource.DataSchemaBean.ParserBean.ParseSpecBean.DimensionsSpecBean(
                Arrays.asList("buyer_id", "book_id", "address_id", "trade_status","pay_id","online_platform","pay_status","pay_date","outer_trade_no","create_date","remark","require_invoice","dml_type"),
                null
                ));

        // MetricsSpecBean
        List<DruidDataSource.DataSchemaBean.MetricsSpecBean> metricsSpecBeans = Arrays.asList(
                new DruidDataSource.DataSchemaBean.MetricsSpecBean("count", "count"),
                new DruidDataSource.DataSchemaBean.MetricsSpecBean("doubleSum", "amount_sum", "order_amount")
        );


        // DataSchemaBean
        DruidDataSource.DataSchemaBean dataSchemaBean = DruidDataSource.DataSchemaBean.builder()
                .dataSource("hawkeye_mysql_t_order")
                .granularitySpec(
                        DruidDataSource.DataSchemaBean.GranularitySpecBean.builder()
                        .queryGranularity("MINUTE")
                        .segmentGranularity("DAY")
                        .rollup(false)
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
                .topic("hawkeye_mysql_t_order")
                .consumerProperties(new DruidDataSource.IoConfigBean.ConsumerPropertiesBean("192.168.1.101:9092"))
                .taskCount(1)
                .replicas(1)
                .build();

        //
        DruidDataSource.TuningConfigBean tuningConfigBean = new DruidDataSource.TuningConfigBean();
        tuningConfigBean.setType("kafka");
        tuningConfigBean.setMaxRowsPerSegment(5000000);

        DruidDataSource druidDataSource = DruidDataSource.builder()
                .dataSchema(dataSchemaBean)
                .ioConfig(ioConfigBean)
                .type("kafka")
                .tuningConfig(tuningConfigBean)
                .context(null)
                .build();
        String result = druidDAO.updateDatasource(druidDataSource);
        log.info(result);
    }

}