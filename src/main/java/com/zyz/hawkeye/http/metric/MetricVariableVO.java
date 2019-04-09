package com.zyz.hawkeye.http.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyz.hawkeye.enums.metric.MetricVariableDataType;
import com.zyz.hawkeye.enums.metric.MetricVariableType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by cheng on 2018/12/18.
 */
@Data
@ApiModel("指标变量")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricVariableVO {


    @ApiModelProperty(value = "指标数据源")
    private MetricSourceVO metricSourceVO;

    @NotNull
    @Valid
    @ApiModelProperty("变量")
    @Size(min = 1)
    private List<Variable> variables;

    @Data
    @ApiModel("变量")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Variable {

        @ApiModelProperty(value = "变量id", hidden = true)
        private Integer id;

        @ApiModelProperty(value = "指标事件源", hidden = true)
        @NotNull
        private Integer eventSourceId;

        @ApiModelProperty("原始数据中的key名（对于嵌套类型可以指定为“A.B.C”）")
        private String fieldName;

        @NotNull
        @ApiModelProperty("变量名")
        private String queryName;

        @NotNull
        @ApiModelProperty("变量作用类型")
        private MetricVariableType variableType;

        @NotNull
        @ApiModelProperty("变量数据类型")
        private MetricVariableDataType dataType;

        @NotNull
        @ApiModelProperty("描述")
        private String description;

        @NotNull
        @ApiModelProperty("是否开启")
        private Boolean enable;

        @NotNull
        @ApiModelProperty("是否从脚本获取")
        private Boolean fromScript;

        @NotNull
        @ApiModelProperty("使用ql表达式")
        private Boolean useQl;

        @NotNull
        @ApiModelProperty("ql表达式")
        private String qlScript;

        @NotNull
        @ApiModelProperty("是否需要去重查询")
        private Boolean useDistinct;

        @ApiModelProperty(value = "创建时间", hidden = true)
        private Date createTime;

        @ApiModelProperty(value = "最后修改时间", hidden = true)
        private Date modifyTime;

    }


    public Optional<Variable> getVariableByNameAndType(String name, MetricVariableType type) {
        return getVariables().stream()
                .filter(v -> v.getQueryName().equals(name) && v.getVariableType().equals(type))
                .findFirst();
    }

    public Optional<Variable> getFilterVariable(String name) {
        Optional<Variable> dimension = getVariables().stream()
                .filter(v -> v.getQueryName().equals(name) && v.getVariableType().equals(MetricVariableType.DIMENSION))
                .findFirst();
        Optional<Variable> metric = getVariables().stream()
                .filter(v -> v.getQueryName().equals(name.substring(0, name.length() - 3)) && v.getVariableType().equals(MetricVariableType.METRIC))
                .findFirst();
        return dimension.isPresent() ? dimension : metric;
    }

}
