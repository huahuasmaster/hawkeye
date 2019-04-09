package com.zyz.hawkeye.dao.druid.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2018/12/21.
 */
@Data
@NoArgsConstructor
public class DruidQueryResult {

    @JSONField(name = "_time")
    DateTime timestamp;
    List<Map<String, Object>> result;


    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = DateTime.parse(timestamp);
    }

    public DruidQueryResult(DateTime timestamp, List<Map<String, Object>> result) {
        this.timestamp = timestamp;
        this.result = result;
    }

}
