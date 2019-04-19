package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dto.BinlogDTO;
import com.zyz.hawkeye.dto.BuryDTO;
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
public class BurySubscriber {
    @Autowired
    private kafkaUtil kafkaUtil;

    @Autowired
    private DruidService druidService;

    @PostConstruct
    private void init() {

        log.info("开始启动线程监听埋点消息");
        KafkaConsumer<String, String> kafkaConsumer = kafkaUtil.newConsumer();
        kafkaConsumer.subscribe(Collections.singleton("bury"));
        Runnable subscribe = () -> {
            while (true) {
                try {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
                    records.forEach(r -> {
                        log.info("key = {}, value = {}", r.key(), r.value());
                        BuryDTO dto = JSON.parseObject(r.value(), BuryDTO.class);
                        if (dto == null) {
                            log.info("解析bury为java类失败");
                            return;
                        }
                        log.info("解析为java类成功:{}", JSON.toJSONString(dto));
                        druidService.compute(dto);
                    });
                } catch (Exception e) { }
            }
        };

        new Thread(subscribe).start();
        log.info("bury消息监听线程启动完毕");
    }
}
