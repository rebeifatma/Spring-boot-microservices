package com.fatma.order.orderLine;


import com.fatma.order.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Data
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer productId;

    private int quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
