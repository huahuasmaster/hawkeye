package com.zyz.hawkeye.http;

import com.zyz.hawkeye.http.metric.ChartVO;
import lombok.Data;

import java.util.List;

@Data
public class DashboardVO {
    private Integer id;
    private String name;
    private String dashboardDesc;
    private Long createDate;
    private Object config;
    private List<ChartVO> chartList;
}
