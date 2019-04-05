package com.zyz.hawkeye.dao.druid.entity;

import com.google.common.collect.Lists;
import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.dimension.DefaultDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PeriodGranularity;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * JavaBean 承载查询Druid时的参数
 * copied from cheng.
 */
@Data
public class DruidQueryParams {

    // 指定维度列输出时的别名和类型。此list元素的数量决定了查询方式:0->TimeSeries, 1->TopN, >1 -> GroupBy
    private List<DefaultDimension> dimensionSpecs = Lists.newArrayList();

    // 指定查询区间，一个Interval包含开始时间和结束时间，即一段时间区间 相当于between
    private List<Interval> intervals;

    // 聚合粒度
    private Granularity granularity;

    // 过滤器，相当于where子句
    private DruidFilter filter;

    // 聚合操作
    private List<DruidAggregator> aggregations = Lists.newArrayList();

    // 后聚合操作
    private List<DruidPostAggregator> postAggregations = Lists.newArrayList();

    // 数据源名称
    private String dataSource;

    // 相当于limit
    private Integer threshold;

    // 额外的一些参数，比如超时时间，查询优先级是否使用缓存等，详见 http://druid.io/docs/latest/querying/query-context.html
    private Context context;
}
