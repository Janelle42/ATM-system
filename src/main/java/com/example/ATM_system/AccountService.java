package com.example.ATM_system;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final Account account;
    private final Account secondAccount;
    private final List<Transaction> transactions;

    public AccountService() {
        account = new Account("123456789", "1234", 10000.00);
        secondAccount = new Account("987654321", "5678", 5000.00);
        transactions = new ArrayList<>();
    }

    public Account getAccount() {
        return account;
    }

    public Account getSecondAccount() {
        return secondAccount;
    }

    public boolean login(String accountNumber, String pin) {
        return account.getAccountNumber().equals(accountNumber)
                && account.getPin().equals(pin);
    }

    public boolean transfer(double amount) {

        if (amount <= 0 || amount > account.getBalance()) {
            return false;
        }

        account.withdraw(amount);
        secondAccount.deposit(amount);

        return true;
    }

    public void addTransaction(String type, double amount) {
        transactions.add(
                new Transaction(type, amount, account.getBalance())
        );
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}