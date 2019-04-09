package com.zyz.hawkeye.http.metric;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyz.hawkeye.enums.metric.MetricAggregationType;
import com.zyz.hawkeye.enums.metric.MetricFilterType;
import com.zyz.hawkeye.enums.metric.MetricPostAggregationType;
import com.zyz.hawkeye.enums.metric.MetricQueryType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author cheng
 * @date 2018/12/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricQueryParamRawVO {

    @NotNull
    @ApiModelProperty("查询类型")
    private MetricQueryType metricQueryType;

    @Valid
    @ApiModelProperty("数据库类型查询必填参数")
    private SqlContext sqlContext;

    @ApiModelProperty("缓存时间(分钟)")
    private Integer cacheTime;

    @NotNull
    @ApiModelProperty("事件源id")
    private Integer eventSourceId;

    @NotNull
    @ApiModelProperty("时间区间")
    @Valid
    private List<Interval> intervals;

    @NotNull
    @ApiModelProperty("GranularityOptions中的时间区间")
    private String period;

    @ApiModelProperty("维度")
    @Valid
    private List<Dimension> dimensions;

    @ApiModelProperty("过滤器")
    @Valid
    private List<Filter> filters;

    @ApiModelProperty("聚合器")
    @Valid
    private List<Aggregation> aggregations;

    @ApiModelProperty("后聚合器")
    @Valid
    private List<PostAggregation> postAggregations;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dimension {

        @NotNull
        @ApiModelProperty("维度")
        private String dimensionFiled;
        @ApiModelProperty("别名（非必填）")
        private String alias;

        public String getDimensionName(String desc) {
            return StringUtils.isEmpty(this.getAlias()) ? desc : this.getAlias();
        }

        public String genSqlColumn() {
            return StringUtils.isNotEmpty(alias) ? dimensionFiled + " as " + alias : dimensionFiled;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Filter {

        @NotNull
        @ApiModelProperty("过滤类型")
        private MetricFilterType metricFilterType;
        @NotNull
        @ApiModelProperty("字段")
        private String field;
        @NotNull
        @ApiModelProperty("过滤参数")
        private String data;
        @ApiModelProperty("使用Having过滤")
        private Boolean useHaving = false;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Aggregation {
        @NotNull
        @ApiModelProperty("度量")
        private String metric;
        @NotNull
        @ApiModelProperty("聚合类型")
        private MetricAggregationType metricAggregationType;
        @NotNull
        @ApiModelProperty("别名（必填）")
        private String alias;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Interval {

        @ApiModelProperty("起始时间")
        @NotNull
        private long startTime;
        @ApiModelProperty("结束时间")
        @NotNull
        private long endTime;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PostAggregation {

        @NotNull
        @ApiModelProperty("输出名称")
        private String name;
        @ApiModelProperty("表达式类型")
        @NotNull
        private MetricPostAggregationType type;
        @NotNull
        @ApiModelProperty("表达式参数1")
        private String firstField;
        @NotNull
        @ApiModelProperty("表达式参数2")
        private String secondField;
    }


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SqlContext {
        @NotNull
        @ApiModelProperty("数据源")
        private String dataSource;
        @NotNull
        @ApiModelProperty("表名")
        private String tableName;
        @NotNull
        @ApiModelProperty("时间字段")
        private String timeColumn;

        @ApiModelProperty("数据库连接tag")
        private String tag;
    }

}
