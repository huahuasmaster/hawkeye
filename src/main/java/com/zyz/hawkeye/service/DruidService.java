package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSONObject;
import com.zyz.hawkeye.dao.druid.DruidDAO;
import com.zyz.hawkeye.dto.BinlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DruidService {

    @Autowired
    private DruidDAO druidDAO;

    public void compute(BinlogDTO binlogDTO) {
        // 1 根据信息找到对应的数据源
        // 2 根据数据源查询对应的指标变量
        // 3 根据指标变量进行计算，产生map
        JSONObject jb = binlogDTO.getData();
        jb.put(DruidDAO.METRIC_COLUMN_TIMESTAMP, binlogDTO.getTs());
        jb.put("dml_type", binlogDTO.getType());
        // 4 将map传输给druid
        druidDAO.insert("hawkeye_mysql_t_order", jb);
    }
}
