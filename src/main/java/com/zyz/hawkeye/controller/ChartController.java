package com.zyz.hawkeye.controller;

import com.zyz.hawkeye.http.HawkeyeResponse;
import com.zyz.hawkeye.http.metric.ChartVO;
import com.zyz.hawkeye.http.metric.MetricParamChartVO;
import com.zyz.hawkeye.http.metric.MetricResultVO;
import com.zyz.hawkeye.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hawkeye/api/charts")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @PostMapping("/{chartId}/data")
    public HawkeyeResponse<MetricResultVO> getByChart(
            @PathVariable("chartId") Integer chartId, @RequestBody MetricParamChartVO paramChartVO) {
        return HawkeyeResponse.success(chartService.getMetricResultByChart(paramChartVO));
    }

    @PostMapping("")
    public HawkeyeResponse<Integer> add(@RequestBody ChartVO chartVO) {
        chartService.save(chartVO);
        return HawkeyeResponse.success(1);
    }



}
