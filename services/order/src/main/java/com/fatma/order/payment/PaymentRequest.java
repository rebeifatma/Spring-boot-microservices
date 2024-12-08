package com.fatma.order.payment;



import com.fatma.order.customer.CustomerResponse;
import com.fatma.order.paymentMethod.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}