package com.example.specificationdemo.service;

import com.example.specificationdemo.request.SearchCustomerRequest;
import com.example.specificationdemo.response.SearchCustomerResponse;

public interface CustomerService {

    SearchCustomerResponse searchCustomer(SearchCustomerRequest request);

}

