package com.sunday.customer.service;

import com.sunday.amqp.RabbitMQMessageProducer;
import com.sunday.clients.fraud.FraudCheckResponse;
import com.sunday.clients.fraud.FraudClient;
import com.sunday.clients.Notification.NotificationRequest;
import com.sunday.customer.dto.CustomerRegistrationRequest;
import com.sunday.customer.entity.Customer;
import com.sunday.customer.exception.NotFoundException;
import com.sunday.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        //check if email is valid
        //check if email is taken
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

//        //check if fraudster
        // using REST TEMPLATE
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//                );

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        // todo: make it async. i.e add a queue
       NotificationRequest notificationRequest = new NotificationRequest(
               customer.getId(),
               customer.getEmail(),
               String.format("Hi %s, welcome to Zeus Hub...", customer.getFirstName()
       )

       );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                 "internal.exchange",
                "internal.notification.routing-key"
        );

    }

    //delete a customer
    public void deleteCustomer (Integer customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new  NotFoundException("Customer does not exist"+ customerId));

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Zeus Hub...", customer.getFirstName()
                )

        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}
