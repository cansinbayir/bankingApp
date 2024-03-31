package com.bankingApp.oredata.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class GetAccountResponse {

    private UUID id;

    private String name;

    private String number;

    private LocalDateTime createdAt;



    public GetAccountResponse(UUID id, String name, String number, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.createdAt = createdAt;
    }



}
