package com.zyz.hawkeye.service;

import com.zyz.hawkeye.dao.druid.DruidDAO;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryParams;
import com.zyz.hawkeye.dao.druid.entity.DruidQueryResult;
import com.zyz.hawkeye.enums.metric.GranularityOptions;
import com.zyz.hawkeye.enums.metric.MetricAggregationType;
import com.zyz.hawkeye.enums.metric.MetricVariableDataType;
import com.zyz.hawkeye.http.metric.*;
import in.zapr.druid.druidry.Interval;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private DruidDAO druidDAO;

    public int save(ChartVO chartVO) {
        return 0;
    }

    public MetricResultVO getMetricResultByChart(MetricParamChartVO metricParamChartVO) {
        // 1查询并检验图表
        // 2根据图表配置生成查询参数
        DruidQueryParams params = new DruidQueryParams();
        params.setDataSource("hawkeye_mysql_t_order");
        List<Interval> intervals = metricParamChartVO.getIntervals().stream()
                .map(i -> new Interval(new DateTime(i.getStartTime()), new DateTime(i.getEndTime())))
                .collect(Collectors.toList());
        params.setIntervals(intervals);
        params.setGranularity(GranularityOptions.fromType(metricParamChartVO.getPeriod()).getGranularity());

        if (metricParamChartVO.getChartId() == 1) {
            params.setAggregations(
                    Collections.singletonList(MetricAggregationType.COUNT.getAggregation(null, "count"))
            );
        } else {
            MetricVariableVO.Variable v = new MetricVariableVO.Variable();
            v.setQueryName("amount_sum");// 在此处是用于查询的字段名,另一个才是查询出的结果的别名
            v.setDataType(MetricVariableDataType.DOUBLE);
            params.setAggregations(
                    Collections.singletonList(MetricAggregationType.SUM.getAggregation(v, "amount_sum"))
            );
        }
        // 3查询得到结果
        List<DruidQueryResult> results = druidDAO.query(params);
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
}
