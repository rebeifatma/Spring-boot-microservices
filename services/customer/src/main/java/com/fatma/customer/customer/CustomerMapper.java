package com.fatma.customer.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        if(request==null)
            return null;
        return customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .adress(request.address())
                .email(request.email())
                .build();
    }
    public CustomerResponse fromCustomer(Customer custmer) {
        return new CustomerResponse(custmer.getId(),
                custmer.getFirstname(),
                custmer.getLastname(),
                custmer.getAdress(),
                custmer.getEmail());
    }


}
