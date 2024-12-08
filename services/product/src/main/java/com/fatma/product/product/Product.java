package com.fatma.product.product;

import com.fatma.product.category.Category;
import jakarta.persistence.*;
import lombok.*;

// Entité Product
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Integer id;

    private String name;
    private String description;
    private int price;
    private double availableQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
