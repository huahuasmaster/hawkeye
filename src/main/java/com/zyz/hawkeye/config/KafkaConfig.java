package com.zyz.hawkeye.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public AdminClient getKafkaAdminClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaServer);
        return KafkaAdminClient.create(properties);
    }

}
