package com.zyz.hawkeye.enums.metric;

import com.zyz.hawkeye.dao.druid.entity.HyperUniqueAggregatorNew;
import com.zyz.hawkeye.http.metric.MetricVariableVO;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.aggregator.LongSumAggregator;
import lombok.Getter;

/**
 * @author cheng
 * @date 2018/12/21
 */
public enum MetricAggregationType {


    MAX("max(%s)", "最大值") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return variable.getDataType().getMaxAggregator(name, variable.getQueryName());
        }
    },
    MIN("min(%s)", "最小值") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return variable.getDataType().getMinAggregator(name, variable.getQueryName());
        }
    },
    SUM("sum(%s)", "总和") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return variable.getDataType().getSumAggregator(name, variable.getQueryName());
        }
    },
    DISTINCT("count(distinct %s)", "去重") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return new HyperUniqueAggregatorNew(name, variable.getQueryName(), true);
        }
    },
    COUNT("count(%s)", "计数") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return new LongSumAggregator(name, "count");
        }
    },;

    @Getter
    private final String expr;
    @Getter
    private final String desc;

    MetricAggregationType(String expr, String desc) {
        this.expr = expr;
        this.desc = desc;
    }

    public abstract DruidAggregator getAggregation(MetricVariableVO.Variable variableVO, String name);

    public String getSqlColumn(String filed, String alias) {
        String format = String.format(this.getExpr(), filed);
        return format + " as " + alias;
    }
}
