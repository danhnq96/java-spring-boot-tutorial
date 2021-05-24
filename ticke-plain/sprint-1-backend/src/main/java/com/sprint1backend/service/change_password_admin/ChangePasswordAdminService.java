package com.sprint1backend.service.change_password_admin;

import com.sprint1backend.entity.AppAccount;

public interface ChangePasswordAdminService {

    AppAccount findById(Long id);

    AppAccount findAppAccountByUsername(String username);

    void save(AppAccount appAccount);
}
