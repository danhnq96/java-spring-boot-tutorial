package com.sprint1backend.service.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1backend.entity.Invoice;
import com.sprint1backend.repository.InvoiceRepository;
import com.sprint1backend.entity.Invoice;
import com.sprint1backend.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Override
    public List<Invoice> findAllInvoice() {
        return null;
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return this.invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Invoice> findInvoiceByIdUser(Long idUser) {
        return this.invoiceRepository.findAllByAppUserId(idUser);
    }
}
