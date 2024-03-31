package com.bankingApp.oredata.service;

import com.bankingApp.oredata.entity.Account;
import com.bankingApp.oredata.entity.Transaction;
import com.bankingApp.oredata.mapper.TransactionMapper;
import com.bankingApp.oredata.model.TransactionDto;
import com.bankingApp.oredata.repository.AccountRepository;
import com.bankingApp.oredata.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionDto initiateTransfer(TransactionDto transactionDto) {
        Optional<Account> optionalSourceAccount = accountRepository.findById(transactionDto.getSourceAccount());
        Optional<Account> optionalTargetAccount = accountRepository.findById(transactionDto.getTargetAccount());

        if (optionalSourceAccount.isPresent() && optionalTargetAccount.isPresent()) {
            Account sourceAccount = optionalSourceAccount.get();
            Account targetAccount = optionalTargetAccount.get();

            // Check if source account has sufficient balance
            if (sourceAccount.getBalance().compareTo(transactionDto.getAmount()) >= 0) {
                // Deduct amount from source account
                sourceAccount.setBalance(sourceAccount.getBalance().subtract(transactionDto.getAmount()));

                // Add amount to target account
                targetAccount.setBalance(targetAccount.getBalance().add(transactionDto.getAmount()));

                // Create transaction record
                Transaction transaction = TransactionMapper.mapToTransaction(transactionDto, targetAccount, sourceAccount);
                transaction.setTransactionDate(LocalDateTime.now());
                transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
                transactionRepository.save(transaction);

                return TransactionMapper.mapToTransactionDto(transaction);
            } else {
                // Insufficient balance
                Transaction transaction = TransactionMapper.mapToTransaction(transactionDto, targetAccount, sourceAccount);
                transaction.setTransactionDate(LocalDateTime.now());
                transaction.setStatus(Transaction.TransactionStatus.FAILED);
                transactionRepository.save(transaction);

                return TransactionMapper.mapToTransactionDto(transaction);
            }
        } else {
            // Account not found
            return null;
        }
    }

    public Set<TransactionDto> getTransactionHistory(UUID accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Set<Transaction> transactions = account.getOutGoingTransactions();
            transactions.addAll(account.getIncomingTransactions());
            return TransactionMapper.mapTransactionDtoSet(transactions);
        } else {
            return null; // Or throw an exception if appropriate
        }
    }
}