package br.com.legalizzr.integration;

import br.com.commons.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "CUSTOMER-ENGINE")
public interface InvoiceIntegration {

    @GetMapping("${api.path.customer.basePath}${api.path.customer.customers}")
    List<Customer> listCustomers();

}
