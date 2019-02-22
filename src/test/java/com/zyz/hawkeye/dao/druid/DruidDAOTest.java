package com.zyz.hawkeye.dao.druid;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.QueryParam;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DruidDAOTest {

    @Autowired
    private DruidDAO druidDAO;

    @Test
    public void listAllDatasource() {
        log.info(druidDAO.listAllDatasourceName());
    }

    @Test
    public void queryTest() {
        DruidQueryParams params = new DruidQueryParams();
        params.setDataSource("wiki");
    }
}