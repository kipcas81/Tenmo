package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {
    Account getAccountById(int accountId);

    BigDecimal getAccountBalance(int accountId);

    Account setAccountBalance(Account account);
}
