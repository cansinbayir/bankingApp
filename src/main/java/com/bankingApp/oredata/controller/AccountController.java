package com.bankingApp.oredata.controller;


import com.bankingApp.oredata.model.AccountDto;
import com.bankingApp.oredata.model.GetAccountResponse;
import com.bankingApp.oredata.service.AccountService;
import com.bankingApp.oredata.service.AuthenticationService;
import com.bankingApp.oredata.service.JwtService;
import com.bankingApp.oredata.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto account) {
        AccountDto createdAccount = accountService.createAccount(account);
        if (createdAccount != null) {
            return new ResponseEntity<>("account created",HttpStatus.CREATED);
        } else  {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create account");
        }
    }

    @GetMapping("/get-accounts")
    public ResponseEntity<Set<GetAccountResponse>> getAccounts(@RequestParam(required = true) UUID userId,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) String number) {
        Set<GetAccountResponse> accounts = new HashSet<>();
        if (userId != null) {
            accounts = accountService.getAccountsByUserId(userId, name, number);
        }
        return  new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable String accountId,
                                           @RequestBody AccountDto updateAccountRequest) {
        boolean isUpdated = accountService.updateAccount(UUID.fromString(accountId), updateAccountRequest);

        if (isUpdated) {
            return new ResponseEntity<>("account updated",HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update account.");
        }
    }

}