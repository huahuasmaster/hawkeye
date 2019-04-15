package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
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
        return 0;
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
        DatasourceEntity datasourceEntity = datasourceRepository.findById(chartEntity.getDatasourceId())
                .orElseThrow(() -> new HawkEyeException("数据源不存在或者已经被关闭"));


        DruidQueryParams params = new DruidQueryParams();
        params.setDataSource(datasourceEntity.getName());
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

    private ChartVO entity2VO(ChartEntity chartEntity) {
        ChartVO chartVO = new ChartVO();
        BeanUtils.copyProperties(chartEntity, chartVO);
        chartVO.setAggregations(JSON.parseArray(chartEntity.getAggregations(), MetricQueryParamRawVO.Aggregation.class));
        chartVO.setDimensions(JSON.parseArray(chartEntity.getDimensions(), MetricQueryParamRawVO.Dimension.class));
        chartVO.setFilters(JSON.parseArray(chartEntity.getFilters(), MetricQueryParamRawVO.Filter.class));
        return chartVO;
    }

}
