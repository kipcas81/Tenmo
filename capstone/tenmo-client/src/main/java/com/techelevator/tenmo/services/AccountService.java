package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.techelevator.tenmo.App.API_BASE_URL;

public class AccountService {

    private static final String BASE_URL = "http://localhost:8080/";

    private static final RestTemplate restTemplate = new RestTemplate();



    public static BigDecimal getAccountBalance(AuthenticatedUser auth) {
        BigDecimal balance = BigDecimal.ZERO;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(auth.getToken());

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<BigDecimal> response = restTemplate.exchange(BASE_URL + "account/2001/balance",
                    HttpMethod.GET, entity, BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

}

