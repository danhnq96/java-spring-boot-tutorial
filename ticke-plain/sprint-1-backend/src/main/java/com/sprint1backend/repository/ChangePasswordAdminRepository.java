package com.sprint1backend.repository;

import com.sprint1backend.entity.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangePasswordAdminRepository extends JpaRepository<AppAccount, Long> {

    AppAccount findAppAccountByUsername(String username);

}
