package com.zyz.hawkeye.http.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyz.hawkeye.enums.metric.MetricQueryType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author cheng
 * @date 2018/12/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartVO {


    @ApiModelProperty(value = "图表id", hidden = true)
    private Integer id;

    @NotNull
    @ApiModelProperty("图表名")
    private String name;

    @NotNull
    @ApiModelProperty("图表类型")
    private String type;

    @NotNull
    @ApiModelProperty("查询类型")
    private MetricQueryType metricQueryType;

    @NotNull
    @ApiModelProperty("聚合条件")
    private List<MetricQueryParamRawVO.Aggregation> aggregations;

    @ApiModelProperty("维度")
    private List<MetricQueryParamRawVO.Dimension> dimensions;

    @ApiModelProperty("过滤条件")
    private List<MetricQueryParamRawVO.Filter> filters;

    @ApiModelProperty("图表展示配置信息")
    private String config;

    @ApiModelProperty("大盘id")
    private Integer dashboardId;

    @ApiModelProperty("最后修改时间")
    private Long lastModifyTime;
}
