package com.fatma.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic orderTopic() {   //newtopic  class  dans  kafka  il  faut ajouter kafka
        return TopicBuilder
                .name("order-topic")
                .build();
    }
}