package com.sprint1backend.service.invoice;

import com.sprint1backend.entity.Invoice;


import java.util.List;

public interface InvoiceService {
    List<Invoice> findAllInvoice();
    Invoice findInvoiceById(Long id);
    List<Invoice>findInvoiceByIdUser(Long idUser);
}
