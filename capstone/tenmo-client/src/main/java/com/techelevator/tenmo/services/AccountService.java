package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private static final String BASE_URL = "http://localhost:8080/";

    private final RestTemplate restTemplate = new RestTemplate();

    public BigDecimal getAccountBalance(int accountId) {
        return restTemplate.getForObject(BASE_URL + "/account/" + accountId + "/balance", Account.class).getBalance();
    }
}
