package com.example.ATM_system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransferController {

    private final AccountService accountService;

    public TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/transfer")
    public String showTransferPage(Model model) {

        Account account = accountService.getAccount();

        model.addAttribute("account", account);

        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(
            @RequestParam String accountNumber,
            @RequestParam double amount,
            Model model) {

        Account account = accountService.getAccount();

        if (!accountNumber.equals(accountService.getSecondAccount().getAccountNumber())) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Account not found.");
            return "transfer";
        }

        if (amount <= 0) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Please enter a valid amount.");
            return "transfer";
        }

        if (!accountService.transfer(amount)) {
            model.addAttribute("account", account);
            model.addAttribute("error", "Insufficient funds.");
            return "transfer";
        }

        accountService.addTransaction("Transfer", amount);

        return "redirect:/dashboard";
    }
}