package com.zyz.hawkeye.config;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.DatasourceRepository;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import com.zyz.hawkeye.service.DatasourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaDruidDataSourceTemplateTest {

    @Autowired
    private DatasourceRepository datasourceRepository;

    @Autowired
    private DatasourceService datasourceService;

    @Test
    public void newInstance() {
        log.info("开始生成json");
        DatasourceVO vo = datasourceService.listAll().get(1);

        String json = JSON.toJSONString(KafkaDruidDataSourceTemplate.newInstance(vo));
        log.info("生成的json如下：{}", json);
    }
}