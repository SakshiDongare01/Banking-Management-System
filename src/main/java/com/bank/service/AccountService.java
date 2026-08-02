package com.bank.service;

import java.util.List;

import com.bank.entity.Account;
import com.bank.entity.Transaction;

public interface AccountService {

    Account saveAccount(Account account);

    List<Account> getAllAccounts();

    void deposit(Long accountNo, Double amount);

    void withdraw(Long accountNo, Double amount);

    Account getAccountByAccountNo(Long accountNo);

    void transferMoney(Long fromAccountNo, Long toAccountNo, Double amount);

    void updateAccount(Account account);

    void deleteAccount(Long accountNo);

    List<Transaction> getAllTransactions();
    
    long getTotalAccounts();

    Double getTotalBalance();
}