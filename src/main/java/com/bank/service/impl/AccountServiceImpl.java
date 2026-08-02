package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public void deposit(Long accountNo, Double amount) {

        Account account = repository.findByAccountNo(accountNo).orElse(null);

        if (account != null) {

            account.setBalance(account.getBalance() + amount);
            repository.save(account);

            // Save Transaction
            Transaction t = new Transaction();
            t.setAccountNo(accountNo);
            t.setType("Deposit");
            t.setAmount(amount);
            t.setDate(java.time.LocalDate.now().toString());

            transactionRepository.save(t);
        }
    }

    @Override
    public void withdraw(Long accountNo, Double amount) {

        Account account = repository.findByAccountNo(accountNo).orElse(null);

        if (account != null && account.getBalance() >= amount) {

            account.setBalance(account.getBalance() - amount);
            repository.save(account);

            // Save Transaction
            Transaction t = new Transaction();
            t.setAccountNo(accountNo);
            t.setType("Withdraw");
            t.setAmount(amount);
            t.setDate(java.time.LocalDate.now().toString());

            transactionRepository.save(t);
        }
    }

    @Override
    public Account getAccountByAccountNo(Long accountNo) {
        return repository.findByAccountNo(accountNo).orElse(null);
    }

    @Override
    public void transferMoney(Long fromAccountNo, Long toAccountNo, Double amount) {

        Account sender = repository.findByAccountNo(fromAccountNo).orElse(null);
        Account receiver = repository.findByAccountNo(toAccountNo).orElse(null);

        if (sender != null && receiver != null && sender.getBalance() >= amount) {

            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            repository.save(sender);
            repository.save(receiver);

            // Sender Transaction
            Transaction t1 = new Transaction();
            t1.setAccountNo(fromAccountNo);
            t1.setType("Transfer Sent");
            t1.setAmount(amount);
            t1.setDate(java.time.LocalDate.now().toString());

            transactionRepository.save(t1);

            // Receiver Transaction
            Transaction t2 = new Transaction();
            t2.setAccountNo(toAccountNo);
            t2.setType("Transfer Received");
            t2.setAmount(amount);
            t2.setDate(java.time.LocalDate.now().toString());

            transactionRepository.save(t2);
        }
    }

    @Override
    public void updateAccount(Account account) {

        Account existing = repository.findByAccountNo(account.getAccountNo()).orElse(null);

        if (existing != null) {

            existing.setName(account.getName());
            existing.setMobile(account.getMobile());
            existing.setAddress(account.getAddress());

            repository.save(existing);
        }
    }

    @Override
    public void deleteAccount(Long accountNo) {

        Account account = repository.findByAccountNo(accountNo).orElse(null);

        if (account != null) {
            repository.delete(account);
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    @Override
    public long getTotalAccounts() {
        return repository.count();
    }

    @Override
    public Double getTotalBalance() {

        Double total = repository.getTotalBalance();

        return total == null ? 0.0 : total;
    }
}