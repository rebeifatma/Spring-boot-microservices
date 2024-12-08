package com.fatma.customer.ErrorResponse.java;

import java.util.Map;
import java.util.Set;

public record ErrorResponse(
        Map<String, String> errors
) {

}