package com.zyz.hawkeye.http.metric;

import lombok.Data;

@Data
public class SqlQueryVO {
    private String query;
    private Boolean header = true;
}
