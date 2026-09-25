package com.example.ATM_system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final AccountService accountService;

    public DashboardController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        Account account = accountService.getAccount();

        model.addAttribute("account", account);

        return "dashboard";
    }
}