package com.zyz.hawkeye.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class BuryDTO {
    @ApiModelProperty("事件名称")
    private String event;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("所在页面")
    private String path;

    @ApiModelProperty("触发时间")
    private Long triggerDate;

    @ApiModelProperty("dom相关属性")
    private Map<String, String> domProperties;

    @ApiModelProperty("相关属性")
    private Map<String, Object> data;
}
