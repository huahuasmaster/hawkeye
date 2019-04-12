package com.zyz.hawkeye.config;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.http.DataSourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaDruidDataSourceTemplateTest {

    @Test
    public void newInstance() {
        DataSourceVO dataSourceVO = new DataSourceVO();
        dataSourceVO.setDimensionList(Arrays.asList("qwe","ewq","reqw","rewdfs"));
        dataSourceVO.setQueryGranularity("MINUTE");
        dataSourceVO.setMetricList(Collections.singletonList("price"));
        dataSourceVO.setType("MYSQL");
        dataSourceVO.setRollUp(true);
        MysqlInfo mysqlInfo = new MysqlInfo();
        mysqlInfo.setTable("test_table");
        dataSourceVO.setSourceInfo(JSON.toJSONString(mysqlInfo));

        log.info(JSON.toJSONString(KafkaDruidDataSourceTemplate.newInstance(dataSourceVO)));
    }
}