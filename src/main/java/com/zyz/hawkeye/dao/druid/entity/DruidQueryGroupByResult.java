package com.zyz.hawkeye.dao.druid.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * Created by cheng on 2018/12/22.
 */
@Data
public class DruidQueryGroupByResult {
    @JSONField(name = "_time")
    DateTime timestamp;

    Map<String, Object> event;


    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = DateTime.parse(timestamp);
    }
}
