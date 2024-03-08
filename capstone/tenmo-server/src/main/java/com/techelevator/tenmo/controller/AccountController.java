package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable int accountId) {
        return accountDao.getAccountById(accountId);
    }
//    @RequestMapping(path = "/account/{accountId}/balance", method = RequestMethod.GET)
//    public BigDecimal getAccountBalance(@PathVariable int accountId) {
//        return accountDao.getAccountBalance(accountId);
//    }
    @RequestMapping(path = "/account/user/{userId}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int userId) {
        return accountDao.getAccountByUserId(userId);
    }
    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.PUT)
    public Account setAccountBalance(@Valid @RequestBody Account account, @PathVariable int accountId) {
        return accountDao.setAccountBalance(account);
    }
}
