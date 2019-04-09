package com.zyz.hawkeye.enums.metric;

import lombok.Getter;

/**
 * @author cheng
 * @date 2019/1/19
 */
public enum MonitoringMetricTagType {

    ARGUMENTS("入参标签"),
    RESULT("返回结果标签");

    @Getter
    private final String desc;

    MonitoringMetricTagType(String desc) {
        this.desc = desc;
    }
}
