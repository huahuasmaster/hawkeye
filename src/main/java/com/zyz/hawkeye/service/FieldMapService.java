package com.zyz.hawkeye.service;

import com.zyz.hawkeye.dao.FieldMapRepository;
import com.zyz.hawkeye.dao.entity.FieldMapEntity;
import com.zyz.hawkeye.exception.HawkEyeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FieldMapService {
    @Autowired
    private FieldMapRepository fieldMapRepository;

    public Map<String, String> getFieldMap(Integer datasourceId) {
        List<FieldMapEntity> entities = fieldMapRepository.findByDatasourceId(datasourceId);
        return entities.stream()
                .collect(Collectors.toMap(
                        FieldMapEntity::getFieldName,
                        (fm) -> (fm.getType().equals("metric") ? "ele" : "dim") + fm.getDruidIndex()));
    }

    public int save(Integer datasourceId, String fieldName, String type) {
        Integer maxIndex = fieldMapRepository.getMaxIndex(datasourceId, type);
        if (maxIndex <= 0) {
            maxIndex = 0;
        }
        if (maxIndex >= 4) {
            throw new HawkEyeException("字段数超过阈值");
        }
        maxIndex++;
        FieldMapEntity entity = new FieldMapEntity();
        entity.setDruidIndex(maxIndex);
        entity.setDatasourceId(datasourceId);
        entity.setFieldName(fieldName);
        entity.setType(type);
        entity = fieldMapRepository.save(entity);
        return entity.getId();
    }

}
