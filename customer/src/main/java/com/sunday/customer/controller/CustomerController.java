package com.sunday.customer.controller;

import com.sunday.customer.dto.CustomerRegistrationRequest;
import com.sunday.customer.entity.Customer;
import com.sunday.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/customers")
@RestController
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    public void deleteCustomer (@PathVariable (name = "CustomerId") Integer CustomerId){
        log.info("deleting customer with id {}", CustomerId);
        customerService.deleteCustomer(CustomerId);
    }
}
