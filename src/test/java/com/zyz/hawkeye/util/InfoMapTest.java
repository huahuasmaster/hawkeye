package com.zyz.hawkeye.util;

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
public class InfoMapTest {

    @Autowired
    private InfoMap infoMap;

    @Test
    public void regist() {
    }

    @Test
    public void registMysql() {
    }

    @Test
    public void registBury() {
    }

    @Test
    public void getByMysqlInfo() {
        log.info(JSON.toJSONString(infoMap.getByMysqlInfo("duangduang", "t_order").get()));
    }

    @Test
    public void getByBuryInfo() {
    }

    @Test
    public void getMysqlMap() {
    }

    @Test
    public void getBuryMap() {
    }
}