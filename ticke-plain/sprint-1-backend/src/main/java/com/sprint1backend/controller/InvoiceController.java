package com.sprint1backend.controller;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.entity.AppUser;
import com.sprint1backend.entity.Invoice;
import com.sprint1backend.repository.AppAccountRepository;
import com.sprint1backend.repository.AppUserRepository;
import com.sprint1backend.repository.InvoiceRepository;
import com.sprint1backend.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@CrossOrigin
public class InvoiceController {
    @Autowired()
    InvoiceService invoiceService;

    @Autowired
    AppAccountRepository appAccountRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping("/findAppUserById/{id}")
    public ResponseEntity<?> findUserByAccountId(@PathVariable Long id) {
        AppAccount account = appAccountRepository.findById(id).orElse(null);
        AppUser appUser = this.appUserRepository.findByAppAccount(account);
        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Invoice>> findAllInvoice() {
        List<Invoice> invoiceList = this.invoiceService.findAllInvoice();
        return new ResponseEntity<>(invoiceList, HttpStatus.OK);
    }

    @GetMapping("/findByInvoiceId/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable Long id) {
        Invoice invoice = this.invoiceService.findInvoiceById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/findAllInvoiceByUserId/{id}")
    public ResponseEntity<?> findInvoiceByUserId(@PathVariable Long id) {
        AppAccount account = appAccountRepository.findById(id).orElse(null);
        AppUser appUser = account.getAppUser();
        List<Invoice> invoiceList = invoiceRepository.findAllByAppUserId(appUser.getId());
//        List<Invoice> invoiceList = this.invoiceService.findInvoiceByIdUser(id);
        return new ResponseEntity<>(invoiceList,HttpStatus.OK);

    }
}
