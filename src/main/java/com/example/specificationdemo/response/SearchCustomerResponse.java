package com.example.specificationdemo.response;

import com.example.specificationdemo.entity.Customer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchCustomerResponse {
    private List<Customer> data;
    private Integer currentPage;
    private Integer total;
    private Integer perPage;
}
