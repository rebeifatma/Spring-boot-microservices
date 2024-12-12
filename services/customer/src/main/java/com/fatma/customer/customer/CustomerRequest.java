package com.fatma.customer.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotNull(message = "Customer ID is required")
        @Pattern(regexp = "\\d+", message = "Customer ID must be numeric")
        String id,

        @NotNull(message = "Customer first name is required")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstname,

        @NotNull(message = "Customer last name is required")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastname,

        @NotNull(message = "Address is required") // Validation pour un objet non nul
        Address address,
        @Email(message="customer email is not valid")
        String email

) {}
