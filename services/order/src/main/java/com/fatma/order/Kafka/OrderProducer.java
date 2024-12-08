package com.fatma.order.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation");

        // Création d'un message avec en-têtes Kafka
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation) // Ajout de la charge utile (payload)
                .setHeader(TOPIC, "order-topic") // Ajout de l'en-tête Kafka pour spécifier le topic
                .build();

        // Envoi du message au topic Kafka
        kafkaTemplate.send(message);
    }
}
