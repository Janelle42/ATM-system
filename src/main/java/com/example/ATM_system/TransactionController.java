package com.example.ATM_system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/transactions")
    public String transactions(Model model) {

        model.addAttribute("account", accountService.getAccount());
        model.addAttribute("transactions", accountService.getTransactions());

        return "transactions";
    }
}