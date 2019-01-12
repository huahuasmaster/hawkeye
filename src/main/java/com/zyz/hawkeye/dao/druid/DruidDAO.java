package com.zyz.hawkeye.dao.druid;

import com.zyz.hawkeye.util.OkHttpClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DruidDAO {

    @Value("${druid.host}")
    private String druidHost;

    @Autowired
    private OkHttpClientWrapper clientWrapper;

    public String listAllDatasource() {
        return clientWrapper.get(druidHost+":8081/druid/coordinator/v1/datasources/");
    }
}
