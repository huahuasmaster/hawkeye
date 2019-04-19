package com.zyz.hawkeye.dao.druid;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.config.KafkaDruidDataSourceTemplate;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryParams;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryResult;
import com.zyz.hawkeye.enums.metric.GranularityOptions;
import com.zyz.hawkeye.enums.metric.MetricAggregationType;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.metric.MetricQueryParamRawVO;
import com.zyz.hawkeye.service.DatasourceService;
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

    @Autowired
    private DatasourceService datasourceService;

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
        params.setGranularity(GranularityOptions.fromType("1小时").getGranularity());
        params.setAggregations(
                Collections.singletonList(MetricAggregationType.COUNT.getAggregation(null, "count"))
        );
        List<DruidQueryResult> result = druidDAO.query(params);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 1);
        result.forEach(r -> log.info(JSON.toJSONString(r)));
    }

    @Test
    public void updateSourceTest() {
        DatasourceVO vo = datasourceService.listAll().get(1);

        druidDAO.updateDatasource(KafkaDruidDataSourceTemplate.newInstance(vo));
    }

}