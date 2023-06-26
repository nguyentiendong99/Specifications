package com.example.specificationdemo.service;

import com.example.specificationdemo.entity.Customer;
import com.example.specificationdemo.repository.CustomerRepository;
import com.example.specificationdemo.request.SearchCustomerRequest;
import com.example.specificationdemo.response.SearchCustomerResponse;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private static final String KEY = "key";
    private static final String NAME = "name";

    @Override
    public SearchCustomerResponse searchCustomer(SearchCustomerRequest request) {
        String keySearch = request.getSearchKey();
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Order.asc(NAME)));
        Specification<Customer> specs = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (KEY.equals(keySearch)) {
                predicates.add(cb.like(root.get(NAME), "%" + request.getSearchValue() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return buildSearchCustomerResponse(customerRepository.findAll(specs, pageable), request.getPageIndex(), request.getPageSize());
    }

    private SearchCustomerResponse buildSearchCustomerResponse(Page<Customer> customers, Integer offset, Integer limit) {
        SearchCustomerResponse response = new SearchCustomerResponse();
        response.setCurrentPage(offset);
        response.setPerPage(limit);
        if (customers == null) {
            response.setTotal(0);
            response.setData(new ArrayList<>());
            return response;
        }
        response.setTotal((int) customers.getTotalElements());
        response.setData(customers.getContent());
        return response;
    }
}
