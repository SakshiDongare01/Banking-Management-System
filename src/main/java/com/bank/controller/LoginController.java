package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.entity.Admin;
import com.bank.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository repository;

    // Open Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Check Login
    @PostMapping("/login")
    public String login(String username,
                        String password,
                        HttpSession session,
                        Model model) {

        Admin admin = repository.findByUsernameAndPassword(username, password);

        if (admin != null) {

            // Store logged-in admin in session
            session.setAttribute("admin", admin);

            return "redirect:/";
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}