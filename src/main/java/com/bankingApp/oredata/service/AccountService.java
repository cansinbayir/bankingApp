package com.bankingApp.oredata.service;


import com.bankingApp.oredata.entity.Account;
import com.bankingApp.oredata.entity.User;
import com.bankingApp.oredata.mapper.AccountMapper;
import com.bankingApp.oredata.model.AccountDto;
import com.bankingApp.oredata.model.GetAccountResponse;
import com.bankingApp.oredata.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService  {


    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto createAccount(AccountDto accountDto) {

        Account account = new Account();
        account.setName(accountDto.getName());
        account.setNumber(accountDto.getNumber());
        account.setBalance(accountDto.getBalance());
        LocalDateTime now = LocalDateTime.now();
        account.setCreatedAt(now);
        account.setUpdatedAt(now);
        User user = new User();
        user.setId(accountDto.getUserId());
        account.setUser(user);


        Account createdAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(createdAccount);

    }

    public Set<GetAccountResponse> getAccountsByUserId(UUID userId, String name, String number) {
        Set<Account> accounts = accountRepository.findByUserId(userId);

        if (name != null && number != null) {
            accounts = accounts.stream()
                    .filter(account -> account.getName().equals(name) && account.getNumber().equals(number))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else if (name != null) {
            accounts = accounts.stream()
                    .filter(account -> account.getName().equals(name))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else if (number != null) {
            accounts = accounts.stream()
                    .filter(account -> account.getNumber().equals(number))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }

        // Sort accounts by createdAt in descending order (most recent first)
        List<Account> sortedAccounts = accounts.stream()
                .sorted(Comparator.comparing(Account::getCreatedAt).reversed())
                .toList();

        // Convert to GetAccountResponse and return as a Set
        return sortedAccounts.stream()
                .map(account -> new GetAccountResponse(account.getId(), account.getName(), account.getNumber(), account.getCreatedAt()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public boolean updateAccount(UUID accountId, AccountDto updateAccountRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setName(updateAccountRequest.getName());
            account.setNumber(updateAccountRequest.getNumber());
            account.setBalance(updateAccountRequest.getBalance());
            //set update dateTime only
            LocalDateTime now = LocalDateTime.now();
            account.setUpdatedAt(now);
            accountRepository.save(account);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteAccount(UUID accountId) {
        try {
            accountRepository.deleteById(accountId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AccountDto getAccountDetails(UUID accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return AccountMapper.mapToAccountDto(account);
        } else {
            return null;
        }
    }

}
