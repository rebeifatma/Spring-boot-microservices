package com.fatma.order.Kafka;

import com.fatma.order.customer.CustomerResponse;
import com.fatma.order.paymentMethod.PaymentMethod;
import com.fatma.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
