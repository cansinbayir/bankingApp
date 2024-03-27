package com.bankingApp.oredata.model;


import lombok.Getter;

@Getter
public class GetAccountResponse {

    private String name;

    private String number;

    public GetAccountResponse(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
