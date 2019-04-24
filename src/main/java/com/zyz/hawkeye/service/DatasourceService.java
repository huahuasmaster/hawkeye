package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.zyz.hawkeye.config.KafkaDruidDataSourceTemplate;
import com.zyz.hawkeye.constants.BuryFields;
import com.zyz.hawkeye.dao.DatasourceRepository;
import com.zyz.hawkeye.dao.FieldMapRepository;
import com.zyz.hawkeye.dao.druid.DruidDAO;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import com.zyz.hawkeye.util.InfoMap;
import com.zyz.hawkeye.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DatasourceService {

    @Autowired
    private DatasourceRepository datasourceRepository;

    @Autowired
    private DruidDAO druidDAO;

    @Autowired
    private FieldMapService fieldMapService;

    @Autowired
    private InfoMap infoMap;

    @Transactional
    public Integer save(DatasourceVO datasourceVO) {
        // 检查冲突

        // 创建druid.datasource
        datasourceVO.setName(getTopic(datasourceVO));
        if (CollectionUtils.isEmpty(datasourceVO.getMetricList())) {
            datasourceVO.setMetricList(Collections.singletonList("count"));
        } else {
            datasourceVO.getMetricList().add("count");
        }


        // 落库
        DatasourceEntity entity = datasourceRepository.save(VO2Entity(datasourceVO));

        // 在infomap中启用
        infoMap.regist(entity);

        // 在字段映射表中保存映射
        if (datasourceVO.getType().equals("BURY")) {
            saveFieldMap(datasourceVO.getMetricList(), "metric", entity.getId());
            saveFieldMap(datasourceVO.getDimensionList(), "dimension", entity.getId());
        } else {
            druidDAO.updateDatasource(KafkaDruidDataSourceTemplate.newInstance(datasourceVO));
        }

        return entity.getId();
    }

    public List<DatasourceVO> listAll() {
        return datasourceRepository.findAll()
                .stream().map(this::entity2VO).collect(Collectors.toList());
    }

    private String getTopic(DatasourceVO dataSourceVO) {
        String suffix;
        switch (DataSourceType.fromType(dataSourceVO.getType())) {
            case MYSQL:
                suffix = dataSourceVO.getSourceInfo().get("table");
                break;
            case BURY:
                suffix = dataSourceVO.getSourceInfo().get("event");
                break;
            default:
                suffix = "";
        }
        return "hawkeye_" + dataSourceVO.getType().toLowerCase() + "_" + suffix;
    }


    private DatasourceVO entity2VO(DatasourceEntity entity) {
        DatasourceVO vo = new DatasourceVO();
        BeanUtils.copyProperties(entity, vo);
        vo.setFieldList(JSON.parseArray(entity.getFieldList(), String.class));
        vo.setMetricList(JSON.parseArray(entity.getMetricList(), String.class));
        vo.setSourceInfo(JSON.parseObject(entity.getConfig(), Map.class));
//        vo.setMetricList(JSON.parseArray(entity.getMetricList(), DruidDataSource.DataSchemaBean.MetricsSpecBean.class));
        vo.setDimensionList(JSON.parseArray(entity.getDimensionList(), String.class));
        vo.setEnable(entity.isEnable());
        vo.setRollUp(entity.getRollUp());
        vo.setDesc(entity.getSourceDesc());
        return vo;
    }

    private DatasourceEntity VO2Entity(DatasourceVO datasourceVO) {
        DatasourceEntity datasourceEntity = new DatasourceEntity();
        datasourceEntity.setName(getTopic(datasourceVO));
        datasourceEntity.setSourceDesc(datasourceVO.getDesc());
        datasourceEntity.setConfig(JSON.toJSONString(datasourceVO.getSourceInfo()));
        datasourceEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        datasourceEntity.setFieldList(JSON.toJSONString(datasourceVO.getFieldList()));
        datasourceEntity.setDimensionList(JSON.toJSONString(datasourceVO.getDimensionList()));
        datasourceEntity.setMetricList(JSON.toJSONString(datasourceVO.getMetricList()));
        datasourceEntity.setEnable(true);
        datasourceEntity.setRollUp(false);
        datasourceEntity.setQueryGranularity("1分钟");
        datasourceEntity.setSample(datasourceVO.getSample());
        datasourceEntity.setType(datasourceVO.getType());
        return datasourceEntity;
    }

    public List<String> listFields(MysqlInfo mysqlInfo) {
        log.info(JSON.toJSONString(mysqlInfo));
        List<String> result = new ArrayList<>();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + mysqlInfo.getHost() + "/" + mysqlInfo.getDatabase() + "?useSSL=false&serverTimezone=GMT%2b8&characterEncoding=utf8");
        dataSource.setUser(mysqlInfo.getUser());
        dataSource.setPassword(mysqlInfo.getPassword());
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet tableRs = dbmd.getTables(null, "%", mysqlInfo.getTable(), new String[]{"TABLE"});
            while (tableRs.next()) {
                if (tableRs.getString("TABLE_NAME").equals(mysqlInfo.getTable())) {
                    ResultSet columnRs = dbmd.getColumns(null, null, mysqlInfo.getTable(), "%");
                    while (columnRs.next()) {
                        String name = columnRs.getString("COLUMN_NAME");
//                        String type = columnRs.getString("TYPE_NAME");
//                        String size = columnRs.getString("COLUMN_SIZE");
//                        String nullable = columnRs.getString("IS_NULLABLE");
//                        String comment = columnRs.getString("REMARKS");
//                        columnInfos.add(
//                                ColumnInfoVO.builder().name(name).type(type).size(size).nullable(nullable).comment(comment)
//                                        .build());
                        result.add(name);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<String> listFields(String sample) {
        if (!JSONUtil.isJson(sample)) {
            throw new HawkEyeException("请检查结构是否为json");
        }
        JSONObject jsonObject = JSONObject.parseObject(sample);
        return JSONUtil.flatToList("", jsonObject);
    }

//    public int switch(boolean wannaEnable)

    private void saveFieldMap(List<String> fields, String type, Integer datasourceId) {
        fields.forEach(field -> {
            if (!BuryFields.DEFINED_FIELDS.contains(field)) {
                // 记录映射
                fieldMapService.save(datasourceId, field, type);
            }
        });

    }

}
