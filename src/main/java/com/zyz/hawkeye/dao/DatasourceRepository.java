package com.zyz.hawkeye.dao;

import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatasourceRepository extends JpaRepository<DatasourceEntity, Integer> {
    List<DatasourceEntity> findByTypeAndEnable(String type, boolean enable);
}
