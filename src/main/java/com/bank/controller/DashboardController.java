package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;

@Controller
public class DashboardController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("accounts",
                accountService.getTotalAccounts());

        model.addAttribute("balance",
                accountService.getTotalBalance());

        model.addAttribute("transactions",
                transactionRepository.count());

        return "dashboard";
    }

}