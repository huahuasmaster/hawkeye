package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.http.metric.MetricParamChartVO;
import com.zyz.hawkeye.http.metric.MetricQueryParamRawVO;
import com.zyz.hawkeye.http.metric.MetricResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ChartServiceTest {

    @Autowired
    private ChartService chartService;

    @Test
    public void getMetricResultByChart() {
        MetricParamChartVO metricParamChartVO = new MetricParamChartVO();
        metricParamChartVO.setChartId(2);
        metricParamChartVO.setIntervals(Collections.singletonList(new MetricQueryParamRawVO.Interval(1554951167000L, 1554972767000L)));
        metricParamChartVO.setPeriod("15分钟");
        MetricResultVO resultVO = chartService.getMetricResultByChart(metricParamChartVO);
        log.info(JSON.toJSONString(resultVO));
    }

    @Test
    public void list() {
        log.info(JSON.toJSONString(chartService.listByDashboardId(1)));
    }
}