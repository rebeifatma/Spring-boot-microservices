package com.fatma.product.product;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.math.BigDecimal;

public record ProductPurchaseResponse (
        Integer productId,
        String name,
        String description,
        int  price,
        double quantity
){
}
