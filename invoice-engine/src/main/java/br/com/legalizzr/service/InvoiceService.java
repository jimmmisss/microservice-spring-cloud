package br.com.legalizzr.service;

import br.com.commons.invoice.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice save(Invoice invoice);
    Invoice findById(Long id);
    List<Invoice> listInvoices();

}
