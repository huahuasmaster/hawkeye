package com.zyz.hawkeye.enums.metric;

import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PeriodGranularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @author cheng
 * @date 2019/1/9
 */
public enum GranularityOptions {


    PT1M("1分钟", "PT1M", 60 * 1000),
    PT2M("2分钟", "PT2M", 60 * 1000 * 2),
    PT5M("5分钟", "PT5M", 60 * 1000 * 5),
    PT10M("10分钟", "PT10M", 60 * 1000 * 10),
    PT15M("15分钟", "PT15M", 60 * 1000 * 15),
    PT30M("30分钟", "PT30M", 60 * 1000 * 30),
    PT1H("1小时", "PT1H", 60 * 60 * 1000),
    PT2H("2小时", "PT2H", 60 * 60 * 1000 * 2),
    PT3H("3小时", "PT3H", 60 * 60 * 1000 * 3),
    PT6H("6小时", "PT6H", 60 * 60 * 1000 * 6),
    PT12H("12小时", "PT12H", 60 * 60 * 1000 * 12),
    P1D("1天", "P1D", 60 * 60 * 1000 * 24),
    ALL("all", "all", Long.MAX_VALUE),;

    @Getter
    private final String name;

    @Getter
    private final String druidGranularity;

    @Getter
    private final long intervalMillisecond;

    GranularityOptions(String name, String druidGranularity, long intervalMillisecond) {
        this.name = name;
        this.druidGranularity = druidGranularity;
        this.intervalMillisecond = intervalMillisecond;
    }


    public static GranularityOptions fromType(String name) {
        for (GranularityOptions type : GranularityOptions.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new UnsupportedOperationException("【GranularityOptions】不支持type:" + name);
    }

    public Granularity getGranularity() {
        if (this.equals(ALL)) {
            return new SimpleGranularity(PredefinedGranularity.ALL);
        } else {
            return PeriodGranularity.builder().period(this.getDruidGranularity()).origin(DateTime.now()).timeZone(DateTimeZone.forID("Asia/Shanghai")).build();
        }
    }
}
