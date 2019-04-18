package com.zyz.hawkeye.enums.metric;

public enum DataSourceType {
    MYSQL("MYSQL", "mysql类型"),
    BURY("BURY", "埋点类型");
    private String type;
    private String desc;

    DataSourceType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static DataSourceType fromType(String type) {
        for (DataSourceType sourceType : DataSourceType.values()) {
            if (sourceType.getType().equals(type)) {
                return sourceType;
            }
        }
            throw new UnsupportedOperationException("【SourceType】不支持type:" + type);
    }
}
