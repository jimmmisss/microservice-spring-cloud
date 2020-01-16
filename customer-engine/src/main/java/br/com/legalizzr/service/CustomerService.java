package br.com.legalizzr.service;

import br.com.commons.customer.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);
    Customer fetchById(Long profileId);
    List<Customer> fetchAllProfiles();

}
