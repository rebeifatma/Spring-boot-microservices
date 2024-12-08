package com.fatma.customer.customer;


import com.fatma.customer.exception.CustomerNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public  String createCustomer(CustomerRequest request){
        var customer= customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomr(CustomerRequest customerRequest) {
        var customer=customerRepository.findById(customerRequest.id())
                        .orElseThrow(()->new CustomerNotFoundException(String.format("cannot find customer with id %d", customerRequest.id())));

        mergeCustomer(customer,customerRequest);
        customerRepository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAdress((request.address()));
        }
    }

    public List<CustomerResponse> findAll() {
        return  customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());

    }

    public Boolean existById(String customerId) {
        return customerRepository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {

        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(String.format("cannot find customer with id %d", customerId)));
    }

    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }
}
