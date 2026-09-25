package com.example.ATM_system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String accountNumber,
            @RequestParam String pin) {

        if (accountService.login(accountNumber, pin)) {
            return "redirect:/dashboard";
        }

        return "redirect:/?error=true";
    }
}