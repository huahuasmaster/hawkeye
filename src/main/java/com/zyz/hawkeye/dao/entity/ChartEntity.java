package com.zyz.hawkeye.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_chart", schema = "hawkeye", catalog = "")
public class ChartEntity {
    private int id;
    private Integer datasourceId;
    private String name;
    private String chartDesc;
    private String type;
    private String aggregations;
    private String dimensions;
    private String filters;
    private String config;
    private String defaultGranularity;
    private Integer threshold;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "datasource_id")
    public Integer getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(Integer datasourceId) {
        this.datasourceId = datasourceId;
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

    @Basic
    @Column(name = "default_granularity")
    public String getDefaultGranularity() {
        return defaultGranularity;
    }

    public void setDefaultGranularity(String defaultGranularity) {
        this.defaultGranularity = defaultGranularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartEntity that = (ChartEntity) o;
        return id == that.id &&
                Objects.equals(datasourceId, that.datasourceId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(chartDesc, that.chartDesc) &&
                Objects.equals(type, that.type) &&
                Objects.equals(aggregations, that.aggregations) &&
                Objects.equals(dimensions, that.dimensions) &&
                Objects.equals(filters, that.filters) &&
                Objects.equals(config, that.config) &&
                Objects.equals(defaultGranularity, that.defaultGranularity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datasourceId, name, chartDesc, type, aggregations, dimensions, filters, config, defaultGranularity);
    }

    @Basic
    @Column(name = "threshold")
    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}
