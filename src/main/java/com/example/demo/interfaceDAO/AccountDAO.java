package com.example.demo.interfaceDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;

public interface AccountDAO extends JpaRepository<Account, String> {

}
