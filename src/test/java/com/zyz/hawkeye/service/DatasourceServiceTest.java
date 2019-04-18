package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.http.MysqlInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DatasourceServiceTest {

    @Autowired
    private DatasourceService datasourceService;

    @Test
    public void list() {
      log.info(JSON.toJSONString(datasourceService.listAll()));
    }

    @Test
    public void listFields() {
        MysqlInfo mysqlInfo = new MysqlInfo();
        mysqlInfo.setTable("t_order");
        mysqlInfo.setDatabase("duangduang");
        mysqlInfo.setHost("192.168.1.101:3307");
        mysqlInfo.setUser("root");
        mysqlInfo.setPassword("123456");

        log.info(JSON.toJSONString(datasourceService.listFields(mysqlInfo)));
    }
    @Test
    public void listField2() {
        String json = "{\n" +
                "    \"name\": \"BeJson\",\n" +
                "    \"url\": \"http://www.bejson.com\",\n" +
                "    \"page\": 88,\n" +
                "    \"isNonProfit\": true,\n" +
                "    \"address\": {\n" +
                "        \"street\": \"科技园路.\",\n" +
                "        \"city\": \"江苏苏州\",\n" +
                "        \"country\": \"中国\"\n" +
                "    },\n" +
                "    \"links\": [\n" +
                "        {\n" +
                "            \"name\": \"Google\",\n" +
                "            \"url\": \"http://www.google.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Baidu\",\n" +
                "            \"url\": \"http://www.baidu.com\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"SoSo\",\n" +
                "            \"url\": \"http://www.SoSo.com\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        log.info(JSON.toJSONString(datasourceService.listFields(json)));

    }
}