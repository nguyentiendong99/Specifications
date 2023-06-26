package com.example.specificationdemo.request;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerRequest {
    private String searchKey;
    private String searchValue;
    private int pageIndex;
    private int pageSize;
}
