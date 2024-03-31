package com.bankingApp.oredata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    public enum TransactionStatusDto {
        SUCCESS,
        FAILED
    }

    private Long id;
    private UUID sourceAccount;
    private UUID targetAccount;
    private BigDecimal amount;
    private TransactionStatusDto status;

    // Constructors, getters, and setters omitted for brevity
}
