package com.bankingApp.oredata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    public enum TransactionStatusDto {
        SUCCESS,
        FAILED
    }
    private Long id;
    private UUID sourceAccount;
    private UUID targetAccount;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionStatusDto status;

}
