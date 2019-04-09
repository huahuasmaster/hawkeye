package com.zyz.hawkeye.enums.metric;

import lombok.Getter;

/**
 *
 * @author cheng
 * @date 2018/12/19
 */
public enum MetricQueryGranularity {

    MINUTE("MINUTE", "分钟"),
    HOUR("HOUR", "小时"),
    ;

    @Getter
    private final String name;
    @Getter
    private final String desc;

    MetricQueryGranularity(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    public static MetricQueryGranularity fromType(String name) {
        for (MetricQueryGranularity type : MetricQueryGranularity.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new UnsupportedOperationException("【MetricQueryGranularity】不支持type:" + name);
    }

}
