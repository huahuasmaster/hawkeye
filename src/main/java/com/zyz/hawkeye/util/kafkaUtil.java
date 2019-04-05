package com.zyz.hawkeye.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Properties;

@Component
public class kafkaUtil {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private AdminClient adminClient;

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    private static final Integer DEFAULT_PARTITION_NUM = 1;

    private static final Short DEAFULT_REPLICATION_FACTOR_NUM = 1;


    public void send(String topic, String content) {
        kafkaTemplate.send(topic, content);
    }

    public void newTopic(String topic) {
        NewTopic newTopic = new NewTopic("topic", DEFAULT_PARTITION_NUM, DEAFULT_REPLICATION_FACTOR_NUM);
        CreateTopicsResult result = adminClient.createTopics(Collections.singletonList(newTopic));
        KafkaFuture future = result.values().get(topic);
        // todo do sth. with the future
    }

    public <K, V> KafkaConsumer<K, V> newConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "1");
        return new KafkaConsumer<>(properties);
    }
}
