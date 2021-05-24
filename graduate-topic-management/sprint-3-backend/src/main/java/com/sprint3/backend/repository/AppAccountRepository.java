package com.sprint3.backend.repository;

import com.sprint3.backend.entity.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
}
