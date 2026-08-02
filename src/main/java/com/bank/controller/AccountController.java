package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.entity.Account;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private AccountService service;
    
    @Autowired
    private TransactionRepository transactionRepository;

    // ================= CREATE ACCOUNT =================

    @GetMapping("/create")
    public String createAccount(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        model.addAttribute("account", new Account());
        return "account";
    }

    @PostMapping("/save")
    public String saveAccount(Account account, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        service.saveAccount(account);
        return "redirect:/accounts";
    }

    // ================= VIEW ACCOUNTS =================

    @GetMapping("/accounts")
    public String viewAccounts(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        model.addAttribute("accounts", service.getAllAccounts());

        return "view-accounts";
    }

    // ================= DEPOSIT =================

    @GetMapping("/deposit")
    public String depositPage(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(Long accountNo, Double amount, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        service.deposit(accountNo, amount);

        return "redirect:/accounts";
    }

    // ================= WITHDRAW =================

    @GetMapping("/withdraw")
    public String withdrawPage(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(Long accountNo, Double amount, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        service.withdraw(accountNo, amount);

        return "redirect:/accounts";
    }

    // ================= BALANCE =================

    @GetMapping("/balance")
    public String balancePage(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        return "balance";
    }

    @PostMapping("/balance")
    public String checkBalance(Long accountNo, Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        Account account = service.getAccountByAccountNo(accountNo);

        model.addAttribute("account", account);

        return "balance";
    }

    // ================= TRANSFER =================

    @GetMapping("/transfer")
    public String transferPage(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(Long fromAccountNo,
                           Long toAccountNo,
                           Double amount,
                           HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        service.transferMoney(fromAccountNo, toAccountNo, amount);

        return "redirect:/accounts";
    }

    // ================= UPDATE =================

    @GetMapping("/update")
    public String updatePage(Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        model.addAttribute("account", new Account());

        return "update-account";
    }

    @PostMapping("/update")
    public String updateAccount(Account account, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        service.updateAccount(account);

        return "redirect:/accounts";
    }

    // ================= DELETE =================

    @GetMapping("/delete")
    public String deletePage(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        return "delete-account";
    }

    @PostMapping("/delete")
    public String deleteAccount(Long accountNo, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        service.deleteAccount(accountNo);

        return "redirect:/accounts";
    }

    // ================= SEARCH =================

    @GetMapping("/search")
    public String searchPage(HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        return "search-account";
    }

    @PostMapping("/search")
    public String searchAccount(Long accountNo, Model model, HttpSession session) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        Account account = service.getAccountByAccountNo(accountNo);

        model.addAttribute("account", account);

        return "search-account";
    }
    
    @GetMapping("/statement")
    public String statementPage() {
        return "statement";
    }

    @PostMapping("/statement")
    public String statement(Long accountNo, Model model) {

        model.addAttribute("transactions",
                transactionRepository.findByAccountNo(accountNo));

        model.addAttribute("accountNo", accountNo);

        return "statement";
    }
    
    
}