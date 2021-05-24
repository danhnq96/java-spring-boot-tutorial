package com.sprint1backend.repository;

import com.sprint1backend.entity.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint1backend.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    AppUser findByAppAccount(AppAccount appAccount);
}
