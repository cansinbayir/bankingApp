package com.bankingApp.oredata.controller;

import com.bankingApp.oredata.model.TransactionDto;
import com.bankingApp.oredata.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> initiateTransfer(@RequestBody TransactionDto transactionDto) {
        TransactionDto initiatedTransaction = transactionService.initiateTransfer(transactionDto);
        if (initiatedTransaction != null) {
            return new ResponseEntity<>(initiatedTransaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/account/{accountId}")
    public ResponseEntity<Set<TransactionDto>> getTransactionHistory(@PathVariable UUID accountId) {
        Set<TransactionDto> transactions = transactionService.getTransactionHistory(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}