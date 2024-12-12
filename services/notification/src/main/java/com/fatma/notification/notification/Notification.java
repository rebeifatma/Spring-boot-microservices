package com.fatma.notification.notification;

import com.fatma.notification.kafka.order.OrderConfirmation;
import com.fatma.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

    // Constructeur complet
    public Notification(String id, NotificationType type, LocalDateTime notificationDate,
                        OrderConfirmation orderConfirmation, PaymentConfirmation paymentConfirmation) {
        this.id = id;
        this.type = type;
        this.notificationDate = notificationDate;
        this.orderConfirmation = orderConfirmation;
        this.paymentConfirmation = paymentConfirmation;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public OrderConfirmation getOrderConfirmation() {
        return orderConfirmation;
    }

    public void setOrderConfirmation(OrderConfirmation orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
    }

    // Builder manuel
    public static class Builder {
        private String id;
        private NotificationType type;
        private LocalDateTime notificationDate;
        private OrderConfirmation orderConfirmation;
        private PaymentConfirmation paymentConfirmation;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder type(NotificationType type) {
            this.type = type;
            return this;
        }

        public Builder notificationDate(LocalDateTime notificationDate) {
            this.notificationDate = notificationDate;
            return this;
        }

        public Builder orderConfirmation(OrderConfirmation orderConfirmation) {
            this.orderConfirmation = orderConfirmation;
            return this;
        }

        public Builder paymentConfirmation(PaymentConfirmation paymentConfirmation) {
            this.paymentConfirmation = paymentConfirmation;
            return this;
        }

        public Notification build() {
            return new Notification(id, type, notificationDate, orderConfirmation, paymentConfirmation);
        }
    }

    // Méthode pour obtenir une instance de Builder
    public static Builder builder() {
        return new Builder();
    }
}
