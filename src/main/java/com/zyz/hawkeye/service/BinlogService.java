package com.zyz.hawkeye.service;

import com.zyz.hawkeye.util.kafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collections;

@Service
@Slf4j
public class BinlogService {

    @Autowired
    private kafkaUtil kafkaUtil;

    @PostConstruct
    private void init() {
        KafkaConsumer<String, String> kafkaConsumer = kafkaUtil.newConsumer();
        kafkaConsumer.subscribe(Collections.singleton("maxwell"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
            records.forEach(r -> log.info("key = {}, value = {}", r.key(), r.value()));
        }
    }
}
