package com.zyz.hawkeye.enums.metric;


import in.zapr.druid.druidry.aggregator.*;
import in.zapr.druid.druidry.dimension.enums.OutputType;
import lombok.Getter;


/**
 *
 * @author cheng
 * @date 2018/12/19
 */
public enum MetricVariableDataType {

    LONG("long", "长整型", OutputType.LONG) {
        @Override
        public DruidAggregator getSumAggregator(String name, String fieldName) {
            return new LongSumAggregator(name, fieldName + SUM);
        }

        @Override
        public DruidAggregator getMaxAggregator(String name, String fieldName) {
            return new LongMaxAggregator(name, fieldName + MAX);
        }

        @Override
        public DruidAggregator getMinAggregator(String name, String fieldName) {
            return new LongMinAggregator(name, fieldName + MIN);
        }
    },
    DOUBLE("double", "浮点型", OutputType.FLOAT) {
        @Override
        public DruidAggregator getSumAggregator(String name, String fieldName) {
            return new DoubleSumAggregator(name, fieldName + SUM);
        }

        @Override
        public DruidAggregator getMaxAggregator(String name, String fieldName) {
            return new DoubleMaxAggregator(name, fieldName + MAX);
        }

        @Override
        public DruidAggregator getMinAggregator(String name, String fieldName) {
            return new DoubleMinAggregator(name, fieldName + MIN);
        }
    },
    STRING("string", "字符串", OutputType.STRING) {
        @Override
        public DruidAggregator getSumAggregator(String name, String fieldName) {
            return null;
        }

        @Override
        public DruidAggregator getMaxAggregator(String name, String fieldName) {
            return null;
        }

        @Override
        public DruidAggregator getMinAggregator(String name, String fieldName) {
            return null;
        }
    },;

    public static final String MAX = "Max";
    public static final String MIN = "Min";
    public static final String SUM = "Sum";
    @Getter
    private final String name;
    @Getter
    private final String desc;
    @Getter
    final OutputType outputType;

    MetricVariableDataType(String name, String desc, OutputType outputType) {
        this.name = name;
        this.desc = desc;
        this.outputType = outputType;
    }

    public static MetricVariableDataType fromType(String name) {
        for (MetricVariableDataType type : MetricVariableDataType.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new UnsupportedOperationException("【MetricVariableDataType】不支持type:" + name);
    }

    public abstract DruidAggregator getSumAggregator(String name, String fieldName);

    public abstract DruidAggregator getMaxAggregator(String name, String fieldName);

    public abstract DruidAggregator getMinAggregator(String name, String fieldName);
}
