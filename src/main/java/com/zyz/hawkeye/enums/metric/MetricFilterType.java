package com.zyz.hawkeye.enums.metric;

import com.zyz.hawkeye.exception.HawkEyeException;
import in.zapr.druid.druidry.SortingOrder;
import in.zapr.druid.druidry.filter.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author cheng
 * @date 2018/12/21
 */
public enum MetricFilterType {


    EQUAL("%s = \'%s\'", "相等") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            return new SelectorFilter(dimension, data);
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    NOT_EQUAL("%s <> \'%s\'", "不相等") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            SelectorFilter selectorFilter = new SelectorFilter(dimension, data);
            return new NotFilter(selectorFilter);
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    GREATER("%s > \'%s\'", "大于") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            SortingOrder sort = MetricVariableType.DIMENSION.equals(metricVariableType) ? SortingOrder.ALPHANUMERIC : SortingOrder.NUMERIC;
            return BoundFilter.builder().dimension(dimension)
                    .lower(data).ordering(sort).lowerStrict(true).upperStrict(false).build();
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    LESS("%s < \'%s\'", "小于") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            SortingOrder sort = MetricVariableType.DIMENSION.equals(metricVariableType) ? SortingOrder.ALPHANUMERIC : SortingOrder.NUMERIC;
            return BoundFilter.builder().dimension(dimension)
                    .upper(data).ordering(sort).lowerStrict(false).upperStrict(true).build();
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    GREATER_AND("%s >= \'%s\'", "大于等于") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            SortingOrder sort = MetricVariableType.DIMENSION.equals(metricVariableType) ? SortingOrder.ALPHANUMERIC : SortingOrder.NUMERIC;
            return BoundFilter.builder().dimension(dimension)
                    .lower(data).ordering(sort).lowerStrict(false).upperStrict(false).build();
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    LESS_AND("%s <= \'%s\'", "小于等于") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            SortingOrder sort = MetricVariableType.DIMENSION.equals(metricVariableType) ? SortingOrder.ALPHANUMERIC : SortingOrder.NUMERIC;
            return BoundFilter.builder().dimension(dimension)
                    .upper(data).ordering(sort).lowerStrict(false).upperStrict(false).build();
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    IN("%s in %s", "多值匹配") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            return new InFilter(dimension, Arrays.asList(data.split(",")));
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            List<String> strings = Arrays.asList(data.split(","));
            String reduce = strings.stream().reduce("(", (r, n) -> r + "\"" + n + "\"" + ",");
            String result = reduce.substring(0, reduce.length() - 1) + ")";
            return String.format(this.getExpr(), dimension, result);
        }
    },
    REGEX("regex", "正则匹配") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            return new RegexFilter(dimension, data);
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            throw new HawkEyeException("[sql生成]失败：当前sql条件不支持正则匹配");
        }
    },
    LIKE("%s like \'%s\'", "like匹配") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            throw new HawkEyeException("[Druid查询语句生成]失败：当前Druid不支持like匹配");
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            return String.format(this.getExpr(), dimension, data);
        }
    },
    BETWEEN("%s between \'%s\' and \'%s\'", "范围条件") {
        @Override
        public DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType) {
            throw new HawkEyeException("[Druid查询语句生成]失败：当前Druid不支持范围条件");
        }

        @Override
        public String getSqlFilter(String dimension, String data) {
            String[] split = data.split(",");
//            GaiaException.check(split.length == 2, "[sql生成]失败，指定返回区间有误：" + data);
            return String.format(this.getExpr(), dimension, split[0], split[1]);
        }
    };

    @Getter
    private final String expr;
    @Getter
    private final String desc;

    MetricFilterType(String expr, String desc) {
        this.expr = expr;
        this.desc = desc;
    }

    public abstract DruidFilter getDruidFilter(String dimension, String data, MetricVariableType metricVariableType);

    public abstract String getSqlFilter(String dimension, String data);


}
