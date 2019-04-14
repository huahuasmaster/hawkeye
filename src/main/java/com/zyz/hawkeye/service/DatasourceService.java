package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.http.BuryInfo;
import com.zyz.hawkeye.http.DataSourceVO;
import com.zyz.hawkeye.http.MysqlInfo;

public class DatasourceService {
    private String getTopic(DataSourceVO dataSourceVO) {
        String suffix;
        switch (DataSourceType.fromType(dataSourceVO.getType())) {
            case MYSQL: suffix = JSON.parseObject(dataSourceVO.getSourceInfo(), MysqlInfo.class).getTable();break;
            case BURY: suffix = JSON.parseObject(dataSourceVO.getSourceInfo(), BuryInfo.class).getEvent();break;
            default: suffix = "";
        }
        return "hawkeye_" + dataSourceVO.getType().toLowerCase() + "_" + suffix;
    }
}
