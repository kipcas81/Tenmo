package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.RegisterUserDto;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;

public interface AccountDao {
    Account getAccountById(int accountId);
    BigDecimal getAccountBalance(int accountId);
    Account getAccountByUserId(int userId);
    Account setAccountBalance(Account account);
}
