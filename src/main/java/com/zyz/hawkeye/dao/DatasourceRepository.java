package com.zyz.hawkeye.dao;

import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasourceRepository extends JpaRepository<DatasourceEntity, Integer> {
}
