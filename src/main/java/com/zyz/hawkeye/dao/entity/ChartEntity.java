package com.zyz.hawkeye.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_chart", schema = "hawkeye", catalog = "")
public class ChartEntity {
    private int id;
    private String name;
    private String desc;
    private String metricQueryType;
    private String type;
    private String aggregations;
    private String dimensions;
    private String filters;
    private String config;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "metric_query_type")
    public String getMetricQueryType() {
        return metricQueryType;
    }

    public void setMetricQueryType(String metricQueryType) {
        this.metricQueryType = metricQueryType;
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
    @Column(name = "aggregations")
    public String getAggregations() {
        return aggregations;
    }

    public void setAggregations(String aggregations) {
        this.aggregations = aggregations;
    }

    @Basic
    @Column(name = "dimensions")
    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    @Basic
    @Column(name = "filters")
    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    @Basic
    @Column(name = "config")
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartEntity that = (ChartEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(metricQueryType, that.metricQueryType) &&
                Objects.equals(type, that.type) &&
                Objects.equals(aggregations, that.aggregations) &&
                Objects.equals(dimensions, that.dimensions) &&
                Objects.equals(filters, that.filters) &&
                Objects.equals(config, that.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, metricQueryType, type, aggregations, dimensions, filters, config);
    }
}
