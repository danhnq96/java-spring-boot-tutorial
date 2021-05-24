package com.endgame.adminservice.repositories;

import com.endgame.adminservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);
}
