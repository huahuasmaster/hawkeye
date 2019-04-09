package com.zyz.hawkeye.http.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyz.hawkeye.enums.metric.MetricQueryGranularity;
import com.zyz.hawkeye.http.DataSourceVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.util.Pair;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


/**
 * Created by cheng on 2018/12/19.
 */
@Data
@ApiModel("指标数据源")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricSourceVO{

    @NotNull
    @ApiModelProperty(value = "事件源id，事件类型必须是'所有变更'")
    private Integer eventSourceId;


    @ApiModelProperty(value = "数据源信息", hidden = true)
    private DataSourceVO dataSourceVO;

    @ApiModelProperty(value = "数据源Samples信息", hidden = true)
    private List<String> samples;

    @NotNull
    @ApiModelProperty("查询最小间隔")
    private MetricQueryGranularity queryGranularity;

    @NotNull
    @ApiModelProperty("存储时间(天)")
    private Integer storageTime;

    @NotNull
    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty(value = "脚本", hidden = true)
    private String script;

    @ApiModelProperty(value = "状态码映射")
    private Map<String, List<Pair<String, String>>> map;

}
