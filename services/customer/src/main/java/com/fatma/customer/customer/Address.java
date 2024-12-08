package com.fatma.customer.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Document
@NoArgsConstructor
@Getter
@Setter
@Validated


public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
