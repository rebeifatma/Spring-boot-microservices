package com.fatma.product.product;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;



public record ProductRequest (

        @NotNull(message = "L'ID ne peut pas être nul")
        Integer id,

        @NotBlank(message = "Le nom ne peut pas être vide")
        String name,

        String description,

        @Min(value = 0, message = "Le prix doit être positif")
        int price,

        @Min(value = 0, message = "La quantité disponible doit être positive")
        double availableQuantity,

        @NotNull(message = "L'ID de la catégorie ne peut pas être nul")
        Integer categoryId

){
}
