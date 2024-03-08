package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransferService {
    private static final String API_BASE_URL = "http://localhost:8080/transfer";

    private static final RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public String getToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static Transfer[] getTransfers(String token) {
        Transfer[] transfers = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

           ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(token), Transfer[].class);
           transfers = response.getBody();
        }
        catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfers;
    }

    private static HttpEntity<Void> makeAuthEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>(headers);
    }
}