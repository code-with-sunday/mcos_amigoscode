package com.sunday.kyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.sunday.clients"
)
public class KYCApplication {

    public static void main(String[] args) {
        SpringApplication.run(KYCApplication.class, args);
    }
}
