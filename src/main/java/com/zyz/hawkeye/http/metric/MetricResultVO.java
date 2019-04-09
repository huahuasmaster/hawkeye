package com.zyz.hawkeye.http.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2018/12/21.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricResultVO {

    @ApiModelProperty("指标数据")
    private List<Result> metricList;

    @ApiModelProperty("图表信息")
    MetricChartVO chartVO;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        @ApiModelProperty("时间")
        Long timestamp;

        @ApiModelProperty("具体数据")
        List<Map<String, Object>> data;
    }

}
