package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findByUsernameAndPassword(String username, String password);

}