package com.example.specificationdemo.controller;

import com.example.specificationdemo.request.SearchCustomerRequest;
import com.example.specificationdemo.response.SearchCustomerResponse;
import com.example.specificationdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/search")
    public ResponseEntity<SearchCustomerResponse> searchCustomer(@RequestBody SearchCustomerRequest request) {
        SearchCustomerResponse response = customerService.searchCustomer(request);
        return ResponseEntity.ok(response);
    }
}
