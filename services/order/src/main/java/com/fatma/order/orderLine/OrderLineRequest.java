package com.fatma.order.orderLine;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        int quantity
) {
}