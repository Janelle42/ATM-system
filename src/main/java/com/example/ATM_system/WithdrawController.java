package com.example.ATM_system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WithdrawController {

    private final AccountService accountService;

    public WithdrawController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/withdraw")
    public String showWithdrawPage(Model model) {

        Account account = accountService.getAccount();
        model.addAttribute("account", account);

        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(
            @RequestParam double amount,
            Model model) {

        Account account = accountService.getAccount();

        if (amount <= 0) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Please enter a valid amount.");
            return "withdraw";
        }

        if (!account.withdraw(amount)) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Insufficient funds.");
            return "withdraw";
        }

        accountService.addTransaction("Withdrawal", amount);

        return "redirect:/dashboard";
    }
}