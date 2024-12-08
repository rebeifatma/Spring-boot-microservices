package com.fatma.product.product;

import com.fatma.product.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {


        return Product.builder()
                .id(request.id())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.id()).build())
                .price(request.price())
                .description(request.description())
                .name(request.name())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return  null;
    }

    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
