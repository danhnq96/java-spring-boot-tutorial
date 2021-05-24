package com.sprint1backend.service.account;

import com.sprint1backend.entity.AppAdmin;
import com.sprint1backend.model.AppAdminDTO;
import com.sprint1backend.repository.AppAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppAdminService {

    @Autowired
    private AppAdminRepository appAdminRepository;

    public AppAdmin convertAppAdminDTO(AppAdminDTO appAdmin) {
        AppAdmin newAppAdmin = new AppAdmin();
        newAppAdmin.setFullName(appAdmin.getFullName());
        newAppAdmin.setBirthday(appAdmin.getBirthday());
        newAppAdmin.setAddress(appAdmin.getAddress());
        newAppAdmin.setEmail(appAdmin.getEmail());
        newAppAdmin.setGender(appAdmin.getGender());
        newAppAdmin.setPhoneNumber(appAdmin.getPhoneNumber());
        newAppAdmin.setAppAccount(appAdmin.getAppAccount());
        return newAppAdmin;
    }
}
