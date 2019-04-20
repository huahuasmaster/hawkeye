package com.zyz.hawkeye.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_dashboard", schema = "hawkeye", catalog = "")
public class DashboardEntity {
    private int id;
    private String name;
    private String dashboardDesc;
    private Timestamp createDate;

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
    @Column(name = "dashboard_desc")
    public String getDashboardDesc() {
        return dashboardDesc;
    }

    public void setDashboardDesc(String dashboardDesc) {
        this.dashboardDesc = dashboardDesc;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardEntity that = (DashboardEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(dashboardDesc, that.dashboardDesc) &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dashboardDesc, createDate);
    }
}
