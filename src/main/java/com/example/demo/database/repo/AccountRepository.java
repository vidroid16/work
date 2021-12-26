package com.example.demo.database.repo;

import com.example.demo.database.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    public Account findByUsername(String username);
}
