package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bank.repository.TransactionRepository;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @GetMapping("/transactions")
    public String transactionPage() {

        return "transactions";

    }

    @PostMapping("/transactions")
    public String showTransactions(Long accountNo, Model model) {

        model.addAttribute("transactions",
                repository.findByAccountNo(accountNo));

        return "transactions";

    }

}