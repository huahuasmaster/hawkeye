package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSONObject;
import com.zyz.hawkeye.dao.druid.DruidDAO;
import com.zyz.hawkeye.dto.BinlogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DruidService {

    @Autowired
    private DruidDAO druidDAO;

    /**
     * 将传入的原始数据，按照用户的意愿（指标变量）进行计算
     * @param binlogDTO
     */
    public void compute(BinlogDTO binlogDTO) {
        // 1 根据信息找到对应的数据源

        // 2 根据数据源查询对应的指标变量
        // 3 根据指标变量进行计算，产生map
        if (binlogDTO.getDatabase().equals("hawkeye") || !binlogDTO.getType().equals("insert")) {
            return;
        }
        JSONObject jb = binlogDTO.getData();
        jb.put(DruidDAO.METRIC_COLUMN_TIMESTAMP, binlogDTO.getTs());
        jb.put("dml_type", binlogDTO.getType());
        // 4 将map传输给druid
        druidDAO.insert("hawkeye_mysql_t_order", jb);
    }
}
