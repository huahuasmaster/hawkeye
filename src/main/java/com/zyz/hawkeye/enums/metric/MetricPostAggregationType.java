package com.zyz.hawkeye.enums.metric;


import in.zapr.druid.druidry.postAggregator.ArithmeticFunction;
import lombok.Getter;


/**
 *
 * @author cheng
 * @date 2018/12/25
 */
public enum MetricPostAggregationType {

    PLUS(ArithmeticFunction.PLUS, "加法") {
        @Override
        public Double operate(Number filed1, Number field2) {
            return filed1.doubleValue() + field2.doubleValue();
        }
    },
    MINUS(ArithmeticFunction.MINUS, "减法") {
        @Override
        public Double operate(Number filed1, Number field2) {
            return filed1.doubleValue() - field2.doubleValue();
        }
    },
    MULTIPLY(ArithmeticFunction.MULTIPLY, "乘法") {
        @Override
        public Double operate(Number filed1, Number field2) {
            return filed1.doubleValue() * field2.doubleValue();
        }
    },
    DIVIDE(ArithmeticFunction.DIVIDE, "除法") {
        @Override
        public Double operate(Number filed1, Number field2) {
            return filed1.doubleValue() / field2.doubleValue();
        }
    },;

    @Getter
    private final ArithmeticFunction function;
    @Getter
    private final String desc;

    MetricPostAggregationType(ArithmeticFunction function, String desc) {
        this.function = function;
        this.desc = desc;
    }

    public abstract Double operate(Number filed1, Number field2);

}
