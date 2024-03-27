package com.bankingApp.oredata.repository;

import com.bankingApp.oredata.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Set<Account> findByUserId(UUID userId);
}
