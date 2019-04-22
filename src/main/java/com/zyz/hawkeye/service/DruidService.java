package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.zyz.hawkeye.dao.druid.DruidDAO;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.dto.BinlogDTO;
import com.zyz.hawkeye.dto.BuryDTO;
import com.zyz.hawkeye.util.InfoMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DruidService {

    public static final String BURY_TOPIC = "hawkeye_bury";

    @Autowired
    private DruidDAO druidDAO;

    @Autowired
    private InfoMap infoMap;

    @Autowired
    private FieldMapService fieldMapService;

    /**
     * 将传入的原始数据，按照用户的意愿（指标变量）进行计算
     *
     * @param binlogDTO
     */
    public void compute(BinlogDTO binlogDTO) {
        if (binlogDTO.getDatabase().equals("hawkeye") || !binlogDTO.getType().equals("insert")) {
            return;
        }

        // 1 根据信息找到对应的数据源
        Optional<DatasourceEntity> entityOptional = infoMap.getByMysqlInfo(binlogDTO.getDatabase(), binlogDTO.getTable());
        if (!entityOptional.isPresent()) {
            return;
        }

        // 2 根据数据源查询对应的指标变量
        // 3 根据指标变量进行计算，产生map

        JSONObject jb = binlogDTO.getData();
        jb.put(DruidDAO.METRIC_COLUMN_TIMESTAMP, binlogDTO.getTs());
//        jb.put("dml_type", binlogDTO.getType());

        // 4 将map传输给druid
        druidDAO.insert(entityOptional.get().getName(), jb);
    }

    public void compute(BuryDTO buryDTO) {
        log.info("监听到主题为{}的埋点数据", buryDTO.getEvent());

        // 1 根据信息找到对应的数据源
        Optional<DatasourceEntity> entityOptional = infoMap.getByBuryInfo(buryDTO.getEvent());
        if (!entityOptional.isPresent()) {
            return;
        }
        DatasourceEntity entity  = entityOptional.get();

        // 获取字段映射
        Map<String, String> fieldmap = fieldMapService.getFieldMap(entity.getId());

        // 2 根据数据源查询对应的指标变量
        List<String> vars = new ArrayList<>(JSON.parseArray(entity.getDimensionList(), String.class));
        vars.addAll(JSON.parseArray(entity.getMetricList(), String.class));
        vars.remove("count");

        // 3 根据指标变量进行计算，产生map
        JSONObject jb = new JSONObject();
        String source = JSON.toJSONString(buryDTO);
        vars.forEach(var -> jb.put(fieldmap.getOrDefault(var, var), JSONPath.read(source, var))); //填入数据时，会启用真实字段名称（dim1, dim2, ele1，ele2）

        jb.put(DruidDAO.METRIC_COLUMN_TIMESTAMP, buryDTO.getTriggerDate() / 1000L);

        // 4 将map传输给druid
        druidDAO.insert(BURY_TOPIC, jb);
//        druidDAO.insert(entityOptional.get().getName(), jb);
    }

}
