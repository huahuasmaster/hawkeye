package com.zyz.hawkeye.enums.metric;

import lombok.Getter;

/**
 * @author cheng
 * @date 2018/12/29
 */
public enum MetricQueryType {

    DRUID("DRUID", "德鲁伊查询", "metricDruidQuery") {
        @Override
        public Long getMaxInterval() {
            return 10000000L;
        }
    },
    MYSQL("MYSQL", "mysql数据库查询", "metricJdbcQuery") {
        @Override
        public Long getMaxInterval() {
            return 10000000L;
        }
    },;

    @Getter
    private final String name;
    @Getter
    private final String desc;
    @Getter
    private final String queryBean;

    MetricQueryType(String name, String desc, String queryBean) {
        this.name = name;
        this.desc = desc;
        this.queryBean = queryBean;
    }

    public static MetricQueryType fromType(String name) {
        for (MetricQueryType type : MetricQueryType.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new UnsupportedOperationException("[MetricQueryType]不支持type:" + name);
    }

    public abstract  Long getMaxInterval();
}
