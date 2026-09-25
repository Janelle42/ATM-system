package com.example.ATM_system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepositController {

    private final AccountService accountService;

    public DepositController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/deposit")
    public String showDepositPage(Model model) {

        Account account = accountService.getAccount();
        model.addAttribute("account", account);

        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(
            @RequestParam double amount,
            Model model) {

        Account account = accountService.getAccount();

        if (amount <= 0) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Please enter a valid amount.");
            return "deposit";
        }

        account.deposit(amount);

        accountService.addTransaction("Deposit", amount);

        return "redirect:/dashboard";
    }
}