package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DatasourceServiceTest {

    @Autowired
    private DatasourceService datasourceService;

    @Test
    public void list() {
      log.info(JSON.toJSONString(datasourceService.list()));
    }
}