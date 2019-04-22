package com.zyz.hawkeye.dao;

import com.zyz.hawkeye.dao.entity.FieldMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldMapRepository extends JpaRepository<FieldMapEntity, Integer> {
    FieldMapEntity findByDatasourceIdAndAndFieldName(Integer datasourceId, String fieldName);
    List<FieldMapEntity> findByDatasourceId(Integer datasourceId);
    @Query("select max(druidIndex) from FieldMapEntity where datasourceId = ?1 and type = ?2")
    Integer getMaxIndex(Integer datasourceId, String type);
}
