package com.bankingApp.oredata.mapper;

import com.bankingApp.oredata.model.TransactionDto;
import com.bankingApp.oredata.entity.Account;
import com.bankingApp.oredata.entity.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getSourceAccount().getId(),
                transaction.getTargetAccount().getId(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                mapToTransactionStatusDto(transaction.getStatus())
        );
    }

    public static Set<TransactionDto> mapTransactionDtoSet(Set<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toSet());
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto, Account targetAccount, Account sourceAccount) {
        return new Transaction(
                transactionDto.getId(),
                sourceAccount,
                targetAccount,
                transactionDto.getAmount(),
                transactionDto.getTransactionDate(),
                mapToTransactionStatus(transactionDto.getStatus())
        );


    }

    private static TransactionDto.TransactionStatusDto mapToTransactionStatusDto(Transaction.TransactionStatus status) {
        return status == Transaction.TransactionStatus.SUCCESS ?
                TransactionDto.TransactionStatusDto.SUCCESS : TransactionDto.TransactionStatusDto.FAILED;
    }

    private static Transaction.TransactionStatus mapToTransactionStatus(TransactionDto.TransactionStatusDto status) {
        return status == TransactionDto.TransactionStatusDto.SUCCESS ?
                Transaction.TransactionStatus.SUCCESS : Transaction.TransactionStatus.FAILED;
    }
}
