package com.bankingApp.oredata.mapper;

import com.bankingApp.oredata.model.AccountDto;
import com.bankingApp.oredata.entity.Account;
import com.bankingApp.oredata.entity.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getNumber(),
                account.getName(),
                account.getBalance(),
                account.getUser().getId()
        );
    }

    public static Set<AccountDto> mapToAccountDtoSet(Set<Account> accounts) {
        if (accounts != null) {
            return accounts.stream()
                    .map(AccountMapper::mapToAccountDto)
                    .collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }

    public static Account mapToAccount(AccountDto accountDto, User user) {
        LocalDateTime now = LocalDateTime.now();
        return new Account(
                accountDto.getId(),
                accountDto.getNumber(),
                accountDto.getName(),
                accountDto.getBalance(),
                now,
                now,
                user,
                null,
                null
        );
    }
}

