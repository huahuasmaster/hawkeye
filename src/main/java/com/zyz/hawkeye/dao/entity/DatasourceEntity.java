package com.zyz.hawkeye.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_datasource", schema = "hawkeye", catalog = "")
public class DatasourceEntity {
    private int id;
    private String type;
    private String name;
    private String chartDesc;
    private String config;
    private boolean enable;
    private Timestamp createDate;
    private String queryGranularity;
    private String fieldList;
    private Integer storageTime;
    private String dimensionList;
    private String metricList;
    private String sample;
    private Boolean rollUp;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "chart_desc")
    public String getChartDesc() {
        return chartDesc;
    }

    public void setChartDesc(String chartDesc) {
        this.chartDesc = chartDesc;
    }

    @Basic
    @Column(name = "config")
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Basic
    @Column(name = "enable")
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "query_granularity")
    public String getQueryGranularity() {
        return queryGranularity;
    }

    public void setQueryGranularity(String queryGranularity) {
        this.queryGranularity = queryGranularity;
    }

    @Basic
    @Column(name = "field_list")
    public String getFieldList() {
        return fieldList;
    }

    public void setFieldList(String fieldList) {
        this.fieldList = fieldList;
    }

    @Basic
    @Column(name = "storage_time")
    public Integer getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Integer storageTime) {
        this.storageTime = storageTime;
    }

    @Basic
    @Column(name = "dimension_list")
    public String getDimensionList() {
        return dimensionList;
    }

    public void setDimensionList(String dimensionList) {
        this.dimensionList = dimensionList;
    }

    @Basic
    @Column(name = "metric_list")
    public String getMetricList() {
        return metricList;
    }

    public void setMetricList(String metricList) {
        this.metricList = metricList;
    }

    @Basic
    @Column(name = "sample")
    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Basic
    @Column(name = "roll_up")
    public Boolean getRollUp() {
        return rollUp;
    }

    public void setRollUp(Boolean rollUp) {
        this.rollUp = rollUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatasourceEntity that = (DatasourceEntity) o;
        return id == that.id &&
                enable == that.enable &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(chartDesc, that.chartDesc) &&
                Objects.equals(config, that.config) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(queryGranularity, that.queryGranularity) &&
                Objects.equals(fieldList, that.fieldList) &&
                Objects.equals(storageTime, that.storageTime) &&
                Objects.equals(dimensionList, that.dimensionList) &&
                Objects.equals(metricList, that.metricList) &&
                Objects.equals(sample, that.sample) &&
                Objects.equals(rollUp, that.rollUp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, chartDesc, config, enable, createDate, queryGranularity, fieldList, storageTime, dimensionList, metricList, sample, rollUp);
    }
}
