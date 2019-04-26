package com.zyz.hawkeye.util;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.DatasourceRepository;
import com.zyz.hawkeye.dao.entity.DatasourceEntity;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.BuryInfo;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.MysqlInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class InfoMap {
    @Autowired
    private DatasourceRepository datasourceRepository;

    @Getter
    private ConcurrentHashMap<MysqlInfo, DatasourceEntity> mysqlMap;

    @Getter
    private ConcurrentHashMap<BuryInfo, DatasourceEntity> buryMap;

    @PostConstruct
    private void init() {
//        executor = new ThreadPoolExecutor(6, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        log.info("开始查询现有的mysql-datasource");
        mysqlMap = new ConcurrentHashMap<>();
        datasourceRepository.findByTypeAndEnable("MYSQL", true)
                .forEach(entity -> mysqlMap.put(JSON.parseObject(entity.getConfig(), MysqlInfo.class), entity));
        log.info("所有可用的mysql数据源查询完毕，一共有{}条", mysqlMap.size());

        log.info("开始查询现有的bury-datasource");
        buryMap = new ConcurrentHashMap<>();
        datasourceRepository.findByTypeAndEnable("BURY", true)
                .forEach(entity -> buryMap.put(JSON.parseObject(entity.getConfig(), BuryInfo.class), entity));
        log.info("所有可用的bury数据源查询完毕，一共有{}条", buryMap.size());
    }

    public void regist(DatasourceEntity datasourceEntity) {
        switch (DataSourceType.fromType(datasourceEntity.getType())) {
            case BURY:
                registBury(datasourceEntity);
                break;
            case MYSQL:
                registMysql(datasourceEntity);
                break;
            default:
                throw new HawkEyeException("不支持此类型");
        }
    }

    public boolean checkDuplicate(DatasourceVO datasourceVO) {
//        switch (DataSourceType.fromType(datasourceVO.getType())) {
//            case BURY:
//                return getByMysqlInfo(datasourceVO.getSourceInfo().get("database"), datasourceVO.get)
//                break;
//            case MYSQL:
//                registMysql(datasourceEntity);
//                break;
//            default:
//                throw new HawkEyeException("不支持此类型");
//        }
        return false;

    }


    public void registMysql(DatasourceEntity datasourceEntity) {
        log.info("开始注册mysql", JSON.toJSONString(datasourceEntity));
        MysqlInfo key = getMysqlKey(datasourceEntity);
//        mysqlMap.remove(key);
        mysqlMap.put(key, datasourceEntity);
        log.info("注册结束");

    }

    public void registBury(DatasourceEntity datasourceEntity) {
        log.info("开始注册bury", JSON.toJSONString(datasourceEntity));
        BuryInfo key = getBuryKey(datasourceEntity);
//        buryMap.remove(key);
        buryMap.put(key, datasourceEntity);
        log.info("注册结束");

    }

    private void unregistMysql(DatasourceEntity datasourceEntity) {
        log.info("开始卸载mysql", JSON.toJSONString(datasourceEntity));
        MysqlInfo key = getMysqlKey(datasourceEntity);
//        mysqlMap.remove(key);
        mysqlMap.remove(key);
        log.info("卸载结束");
    }

    public void unregistBury(DatasourceEntity datasourceEntity) {
        log.info("开始卸载bury", JSON.toJSONString(datasourceEntity));
        BuryInfo key = getBuryKey(datasourceEntity);
//        buryMap.remove(key);
        buryMap.remove(key);
        log.info("卸载结束");

    }

    public void unregist(DatasourceEntity datasourceEntity) {
        switch (DataSourceType.fromType(datasourceEntity.getType())) {
            case MYSQL:unregistMysql(datasourceEntity);break;
            case BURY:unregistBury(datasourceEntity);break;
        }
    }


    public MysqlInfo getMysqlKey(DatasourceEntity datasourceEntity) {
        return JSON.parseObject(datasourceEntity.getConfig(), MysqlInfo.class);
    }

    public BuryInfo getBuryKey(DatasourceEntity datasourceEntity) {
        return JSON.parseObject(datasourceEntity.getConfig(), BuryInfo.class);
    }

    public Optional<DatasourceEntity> getByMysqlInfo(String database, String table) {
        MysqlInfo key = new MysqlInfo();
        key.setDatabase(database);
        key.setTable(table);
        return Optional.ofNullable(mysqlMap.get(key));
    }

    public Optional<DatasourceEntity> getByBuryInfo(String event) {
        BuryInfo key = new BuryInfo();
        key.setEvent(event);
        return Optional.ofNullable(buryMap.get(key));
    }
}
