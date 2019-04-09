package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dto.BinlogDTO;
import com.zyz.hawkeye.util.kafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
@Slf4j
public class BinlogService {

    @Autowired
    private kafkaUtil kafkaUtil;

    @Autowired
    private DruidService druidService;

    @PostConstruct
    private void init() {
        KafkaConsumer<String, String> kafkaConsumer = kafkaUtil.newConsumer();
        kafkaConsumer.subscribe(Collections.singleton("maxwell"));
        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
                records.forEach(r -> {
                    log.info("key = {}, value = {}", r.key(), r.value());
                    BinlogDTO dto = JSON.parseObject(r.value(), BinlogDTO.class);
                    if(dto == null) {
                        log.info("解析binlog为java类失败");
                        return;
                    }
                    log.info("解析成功:{}", JSON.toJSONString(dto));
                    druidService.compute(dto);
                });
            }
        }).start();

    }
}
