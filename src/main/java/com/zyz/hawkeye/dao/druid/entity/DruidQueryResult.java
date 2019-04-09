package com.zyz.hawkeye.dao.druid.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2018/12/21.
 */
@Data
@NoArgsConstructor
public class DruidQueryResult {

    DateTime timestamp;
    List<Map<String, Object>> result;


    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        System.out.println(timestamp);
        this.timestamp = new DateTime(timestamp);
//        this.timestamp = DateTime.parse(timestamp, DateTimeFormat.forPattern("yyyy-MM-ddTHH:mm:ss.sssz"));
    }

    public DruidQueryResult(DateTime timestamp, List<Map<String, Object>> result) {
        this.timestamp = timestamp;
        this.result = result;
    }

}
