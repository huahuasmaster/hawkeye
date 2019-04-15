package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.DatasourceRepository;
import com.zyz.hawkeye.dao.druid.entity.DruidDataSource;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.http.BuryInfo;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DatasourceService {

    @Autowired
    private DatasourceRepository datasourceRepository;

    private String getTopic(DatasourceVO dataSourceVO) {
        String suffix;
        switch (DataSourceType.fromType(dataSourceVO.getType())) {
            case MYSQL: suffix = JSON.parseObject(dataSourceVO.getSourceInfo(), MysqlInfo.class).getTable();break;
            case BURY: suffix = JSON.parseObject(dataSourceVO.getSourceInfo(), BuryInfo.class).getEvent();break;
            default: suffix = "";
        }
        return "hawkeye_" + dataSourceVO.getType().toLowerCase() + "_" + suffix;
    }

    public List<DatasourceVO> list() {
        List<DatasourceVO> result = new ArrayList<>();
        List<DatasourceEntity> entities = datasourceRepository.findAll();
        if (!CollectionUtils.isEmpty(entities)) {
            entities.forEach(entity -> result.add(entity2VO(entity)));
        }
        return result;

    }

    private DatasourceVO entity2VO(DatasourceEntity entity) {
        DatasourceVO vo = new DatasourceVO();
        BeanUtils.copyProperties(entity, vo);
        vo.setFieldList(JSON.parseArray(entity.getFieldList(), String.class));
        vo.setMetricList(JSON.parseArray(entity.getMetricList(), String.class));
//        vo.setMetricList(JSON.parseArray(entity.getMetricList(), DruidDataSource.DataSchemaBean.MetricsSpecBean.class));
        vo.setDimensionList(JSON.parseArray(entity.getDimensionList(), String.class));
        vo.setEnable(entity.isEnable());
        vo.setRollUp(entity.getRollUp());
        return vo;
    }
}
