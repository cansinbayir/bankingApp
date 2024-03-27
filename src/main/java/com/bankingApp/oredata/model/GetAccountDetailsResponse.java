package com.bankingApp.oredata.model;


import lombok.Getter;

@Getter
public class GetAccountDetailsResponse {

    private AccountDto accountDetailsResponse;

    public GetAccountDetailsResponse(AccountDto accountDetailsResponse) {
        this.accountDetailsResponse = accountDetailsResponse;
    }
}
