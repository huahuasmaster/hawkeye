package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.zyz.hawkeye.dao.DatasourceRepository;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.BuryInfo;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import com.zyz.hawkeye.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        if(!JSONUtil.isJson(sample)){
            throw new HawkEyeException("请检查结构是否为json");
        }
        JSONObject jsonObject = JSONObject.parseObject(sample);
        return JSONUtil.flatToList("", jsonObject);
    }
}
