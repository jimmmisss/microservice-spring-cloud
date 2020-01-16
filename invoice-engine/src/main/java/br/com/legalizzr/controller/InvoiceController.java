package br.com.legalizzr.controller;

import br.com.commons.invoice.Invoice;
import br.com.commons.customer.Customer;
import br.com.legalizzr.integration.InvoiceIntegration;
import br.com.legalizzr.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class InvoiceController {

    public static final Integer VINTE = 20;
    public static final boolean CLOSE_INVOICE = true;

    private final InvoiceService invoiceService;
    private final InvoiceIntegration invoiceIntegration;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceIntegration invoiceIntegration) {
        this.invoiceService = invoiceService;
        this.invoiceIntegration = invoiceIntegration;
    }

    @GetMapping(value = "/customers")
    @PreAuthorize("hasAuthority('create_profile')")
    public List<Customer> listCustomers() {
        return invoiceIntegration.listCustomers();
    }

    @GetMapping(value = "/invoices")
    @PreAuthorize("hasAuthority('create_profile')")
    public List<Invoice> listInvoices() {
        return invoiceService.listInvoices();
    }

    @PostMapping("/invoice")
    @PreAuthorize("hasAuthority('create_profile')")
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice) {
        List<Customer> listResponseEntity = listCustomers();
        for (Customer c: listResponseEntity) {
            if (c.getCutDay() > VINTE) {
                invoice.setCustomer(c.getId());
                invoice.setCloseInvoice(CLOSE_INVOICE);
                invoice.setDate(new Date(System.currentTimeMillis()));
                invoiceService.save(invoice);
            }
        }
        return ResponseEntity.ok().build();
    }

}
