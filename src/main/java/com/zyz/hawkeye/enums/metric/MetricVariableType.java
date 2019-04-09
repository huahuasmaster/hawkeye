package com.zyz.hawkeye.enums.metric;

import com.google.common.collect.Lists;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import com.zyz.hawkeye.http.metric.MetricVariableVO;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cheng on 2018/12/17.
 */
public enum MetricVariableType {


    METRIC("metric", "度量") {
        @Override
        public void alterSchema(DruidDataSource source, MetricVariableVO.Variable variable) {
            if (variable.getUseDistinct()) {
                addHyperUniquer(source, variable);
                return;
            }
            List<DruidDataSource.DataSchemaBean.MetricsSpecBean> metricsSpec = source.getDataSchema().getMetricsSpec();
            List<DruidDataSource.DataSchemaBean.MetricsSpecBean> collect = METRIC_TYPE.stream().map(type -> {
                DruidDataSource.DataSchemaBean.MetricsSpecBean metricsSpecBean = new DruidDataSource.DataSchemaBean.MetricsSpecBean();
                metricsSpecBean.setType(variable.getDataType().getName() + type);
                metricsSpecBean.setName(variable.getQueryName() + type);
                metricsSpecBean.setFieldName(variable.getQueryName());
                return metricsSpecBean;
            }).collect(Collectors.toList());
            metricsSpec.addAll(collect);

        }
    },
    DIMENSION("dimension", "维度") {
        @Override
        public void alterSchema(DruidDataSource source, MetricVariableVO.Variable variable) {
            List<String> dimensions = source.getDataSchema().getParser().getParseSpec().getDimensionsSpec().getDimensions();
            dimensions.add(variable.getQueryName());
        }
    },;

    protected static final List<String> METRIC_TYPE = Lists.newArrayList("Max", "Sum", "Min");

    @Getter
    private final String name;
    @Getter
    private final String desc;

    MetricVariableType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static MetricVariableType fromType(String type) {
        for (MetricVariableType variableType : MetricVariableType.values()) {
            if (variableType.getName().equals(type)) {
                return variableType;
            }
        }
        throw new UnsupportedOperationException("【MetricVariableType】不支持type:" + type);
    }


    public abstract void alterSchema(DruidDataSource source, MetricVariableVO.Variable variable);

    public static void addHyperUniquer(DruidDataSource source, MetricVariableVO.Variable variable) {
        List<DruidDataSource.DataSchemaBean.MetricsSpecBean> metricsSpec = source.getDataSchema().getMetricsSpec();
        DruidDataSource.DataSchemaBean.MetricsSpecBean metricsSpecBean = new DruidDataSource.DataSchemaBean.MetricsSpecBean();
        metricsSpecBean.setType("hyperUnique");
        metricsSpecBean.setIsInputHyperUnique(false);
        metricsSpecBean.setFieldName(variable.getQueryName());
        metricsSpecBean.setName(variable.getQueryName());
        metricsSpec.add(metricsSpecBean);
    }
}
