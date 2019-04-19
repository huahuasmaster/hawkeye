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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DruidService {

    @Autowired
    private DruidDAO druidDAO;

    @Autowired
    private InfoMap infoMap;

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
        jb.put("dml_type", binlogDTO.getType());

        // 4 将map传输给druid
        druidDAO.insert(entityOptional.get().getName(), jb);
    }

    public void compute(BuryDTO buryDTO) {

        // 1 根据信息找到对应的数据源
        Optional<DatasourceEntity> entityOptional = infoMap.getByBuryInfo(buryDTO.getEvent());
        if (!entityOptional.isPresent()) {
            return;
        }

        // 2 根据数据源查询对应的指标变量
        DatasourceEntity entity  = entityOptional.get();
        List<String> vars = new ArrayList<>(JSON.parseArray(entity.getDimensionList(), String.class));
        vars.addAll(JSON.parseArray(entity.getMetricList(), String.class));

        // 3 根据指标变量进行计算，产生map
        JSONObject jb = new JSONObject();
        String source = JSON.toJSONString(buryDTO);
        vars.forEach(var -> jb.put(var, JSONPath.read(source, var)));

        jb.put(DruidDAO.METRIC_COLUMN_TIMESTAMP, buryDTO.getTriggerDate() / 1000L);

        // 4 将map传输给druid
        druidDAO.insert(entityOptional.get().getName(), jb);
    }
}
