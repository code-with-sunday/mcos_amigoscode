package com.sunday.fraud.controller;

import com.sunday.fraud.dto.FraudCheckResponse;
import com.sunday.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping (path = "{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer =
                fraudCheckService.isFraudulentCustomer(customerId);

        log.info("fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
