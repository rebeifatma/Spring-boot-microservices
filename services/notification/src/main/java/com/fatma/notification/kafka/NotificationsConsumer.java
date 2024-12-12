package com.fatma.notification.kafka;

import com.fatma.notification.email.EmailService;
import com.fatma.notification.kafka.order.OrderConfirmation;
import com.fatma.notification.kafka.payment.PaymentConfirmation;
import com.fatma.notification.notification.Notification;
import com.fatma.notification.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.fatma.notification.notification.NotificationType.ORDER_CONFIRMATION;
import static com.fatma.notification.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
public class NotificationsConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationsConsumer.class);

    private final NotificationRepository repository;
    private final EmailService emailService;

    // Injection par constructeur
    public NotificationsConsumer(NotificationRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming message from payment-topic: {}", paymentConfirmation);

        // Enregistrer la notification
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // Récupérer les informations client
        String customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();

        // Envoyer l'email
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming message from order-topic: {}", orderConfirmation);

        // Enregistrer la notification
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // Récupérer les informations client
        String customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();

        // Envoyer l'email
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
