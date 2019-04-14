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


    MAX("max", "最大值") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return variable.getDataType().getMaxAggregator(name, variable.getQueryName());
        }
    },
    MIN("min", "最小值") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return variable.getDataType().getMinAggregator(name, variable.getQueryName());
        }
    },
    SUM("sum", "总和") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return variable.getDataType().getSumAggregator(name, variable.getQueryName());
        }
    },
    DISTINCT("distinct", "去重") {
        @Override
        public DruidAggregator getAggregation(MetricVariableVO.Variable variable, String name) {
            return new HyperUniqueAggregatorNew(name, variable.getQueryName(), true);
        }
    },
    COUNT("count", "计数") {
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

    public static MetricAggregationType fromExpr(String expr) {
        for (MetricAggregationType type : MetricAggregationType.values()) {
            if (type.getExpr().equals(expr)) {
                return type;
            }
        }
        throw new UnsupportedOperationException("【MetricAggregationType】不支持type:" + expr);
    }

    public abstract DruidAggregator getAggregation(MetricVariableVO.Variable variableVO, String name);

    public String getSqlColumn(String filed, String alias) {
        String format = String.format(this.getExpr(), filed);
        return format + " as " + alias;
    }
}
