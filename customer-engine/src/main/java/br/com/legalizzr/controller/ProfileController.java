package br.com.legalizzr.controller;

import br.com.commons.customer.Customer;
import br.com.legalizzr.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class ProfileController {

    private final CustomerService customerService;

    @Autowired
    public ProfileController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customer")
    public ResponseEntity<Customer> fetch(@RequestParam Long profileId) {
        return ResponseEntity.ok(customerService.fetchById(profileId));
    }

    @GetMapping(value = "/customers")
    @PreAuthorize("hasAuthority('create_profile')")
    public ResponseEntity<List<Customer>> fetch() {
        return ResponseEntity.ok(customerService.fetchAllProfiles());
    }

    @PostMapping("/customer")
    @PreAuthorize("hasAuthority('create_profile')")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }

}
