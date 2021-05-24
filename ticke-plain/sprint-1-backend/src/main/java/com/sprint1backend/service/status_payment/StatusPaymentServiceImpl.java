package com.sprint1backend.service.status_payment;

import com.sprint1backend.entity.StatusPayment;
import com.sprint1backend.repository.StatusPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sprint1backend.common.AppUtils.CANCEL;

@Service
public class StatusPaymentServiceImpl implements StatusPaymentService {
    @Autowired
    private StatusPaymentRepository statusPaymentRepository;

    @Override
    public void save(StatusPayment statusPayment) {
//        this.statusPaymentRepository.save(statusPayment);
    }

    @Override
    public void edit(StatusPayment statusPayment) {
//        this.statusPaymentRepository.save(statusPayment);
    }

    @Override
    public void delete(Long id) {
//        this.statusPaymentRepository.deleteById(id);
    }

    @Override
    public StatusPayment findById(Long id) {
        return this.statusPaymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<StatusPayment> findAll() {
        return this.statusPaymentRepository.findAll();
    }

    @Override
    public StatusPayment findByName(String name) {
        return this.statusPaymentRepository.findByName(name);
    }

}
