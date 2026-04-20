package com.devion.velorent.service.impl;

import com.devion.velorent.repository.CustomerRepository;
import com.devion.velorent.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

}
