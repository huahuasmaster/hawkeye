package com.zyz.hawkeye.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据源view 对象.
 *
 * @author liuyihan
 * @since 2018/12/18
 */
@Data
@ApiModel("数据源信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatasourceVO {

    private Integer id;

    @NotNull
    @ApiModelProperty("数据源类型：MYSQL、BURY")
    private String type;

    @ApiModelProperty("数据源名称")
    private String name;

    @ApiModelProperty("数据源描述")
    private String desc;

    @ApiModelProperty("数据源配置信息,json格式。MYSQL:{address+database+table},BURY:{event}")
    private String sourceInfo;

//    @ApiModelProperty("字段与业务主键绑定,json格式。key-业务键id，value-数据源中对应的字段。支持多个绑定")
//    private List<DicInfoDTO> bizMainKeys;

//    @ApiModelProperty("es中作为索引的字段")
//    private String esIndex;

//    @ApiModelProperty("es中业务主键信息,json格式。key-业务键id，value-数据源中对应的字段。")
//    private DicInfoDTO esBizKey;

    @ApiModelProperty("是否启用")
    private Boolean enable;

//    @ApiModelProperty("数据源用户")
//    private String owner;

//    @ApiModelProperty("数据模型id")
//    private Integer schemaId;

    @ApiModelProperty("数据源列名列表")
    private List<String> fieldList;

    @ApiModelProperty("维度列表")
    private List<String> dimensionList;

    @ApiModelProperty("指标列表")
//    private List<DruidDataSource.DataSchemaBean.MetricsSpecBean> metricList;
    private List<String> metricList;

    @ApiModelProperty("数据源样例")
    private String sample;

//    @ApiModelProperty("子数据源列表--用于ES联合数据源")
//    private List<Integer> childDsIds;

    @ApiModelProperty("创建时间")
    private Long createTime;

    private Boolean rollUp;

    private String queryGranularity;

}
