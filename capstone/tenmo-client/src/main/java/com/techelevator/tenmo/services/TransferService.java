package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;

public class TransferService {
    private static final String API_BASE_URL = "http://localhost:8080/transfer";

    private final RestTemplate restTemplate = new RestTemplate();

    
}