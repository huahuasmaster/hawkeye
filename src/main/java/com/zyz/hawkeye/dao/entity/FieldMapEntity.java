package com.zyz.hawkeye.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_field_map", schema = "hawkeye", catalog = "")
public class FieldMapEntity {

    private int id;
    private int druidIndex;
    private int datasourceId;
    private String fieldName;
    private String type;

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
    @Column(name = "druid_index")
    public int getDruidIndex() {
        return druidIndex;
    }

    public void setDruidIndex(int druidIndex) {
        this.druidIndex = druidIndex;
    }

    @Basic
    @Column(name = "datasource_id")
    public int getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(int datasourceId) {
        this.datasourceId = datasourceId;
    }

    @Basic
    @Column(name = "field_name")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldMapEntity that = (FieldMapEntity) o;
        return id == that.id &&
                druidIndex == that.druidIndex &&
                datasourceId == that.datasourceId &&
                Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, druidIndex, datasourceId, fieldName);
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
