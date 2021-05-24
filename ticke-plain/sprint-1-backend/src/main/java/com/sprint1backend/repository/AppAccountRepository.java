package com.sprint1backend.repository;


import com.sprint1backend.entity.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {

    AppAccount findByUsername(String username);

    AppAccount findByVerificationCode(String code);
}