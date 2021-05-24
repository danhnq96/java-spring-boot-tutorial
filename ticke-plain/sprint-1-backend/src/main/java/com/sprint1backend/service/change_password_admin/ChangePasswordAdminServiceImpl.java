package com.sprint1backend.service.change_password_admin;

import com.sprint1backend.entity.AppAccount;
import com.sprint1backend.repository.ChangePasswordAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordAdminServiceImpl implements ChangePasswordAdminService {

    @Autowired
    private ChangePasswordAdminRepository changePasswordAdminRepository;

    @Override
    public AppAccount findById(Long id) {
        return this.changePasswordAdminRepository.findById(id).orElse(null);
    }

    @Override
    public AppAccount findAppAccountByUsername(String username) {
        return this.changePasswordAdminRepository.findAppAccountByUsername(username);
    }

    @Override
    public void save(AppAccount appAccount) {
        this.changePasswordAdminRepository.save(appAccount);
    }
}
