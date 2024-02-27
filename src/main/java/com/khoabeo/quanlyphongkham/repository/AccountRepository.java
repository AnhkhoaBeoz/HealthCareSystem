package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
   Optional<Account> findAccountByEmailOrUserName(String email, String userName);
   Optional<Account> findAccountByUserName (String username);
}
