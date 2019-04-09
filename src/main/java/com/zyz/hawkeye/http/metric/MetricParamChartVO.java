package com.zyz.hawkeye.http.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cheng
 * @date 2018/12/25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricParamChartVO {

    @NotNull
    @ApiModelProperty("时间区间")
    @Valid
    private List<MetricQueryParamRawVO.Interval> intervals;

    @NotNull
    @ApiModelProperty("GranularityOptions中的时间区间")
    private String period;

    @NotNull
    @ApiModelProperty("图表id")
    private Integer chartId;

    @ApiModelProperty("额外过滤条件")
    private List<MetricQueryParamRawVO.Filter> filters;

}
