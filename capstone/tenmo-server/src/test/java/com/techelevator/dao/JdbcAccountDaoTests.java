package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcAccountDaoTests  extends BaseDaoTests {

    private static final BigDecimal STARTING_BALANCE = new BigDecimal("1000.00");
    private static final BigDecimal NEW_BALANCE = new BigDecimal("500.00");
//    STARTING_BALANCE.setScale(2);
    private static final Account ACCOUNT_1 = new Account(2001, 1001, STARTING_BALANCE);
    private static final Account ACCOUNT_1_1 = new Account(2001, 1001, NEW_BALANCE);
    private static final Account ACCOUNT_2 = new Account(2002, 1002, STARTING_BALANCE);
    private static final Account ACCOUNT_2_1 = new Account(4002, 1002, NEW_BALANCE);
    private static final Account ACCOUNT_3 = new Account(2003, 1003, STARTING_BALANCE);

    private JdbcAccountDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }

    @Test
    public void getAccountById_given_valid_Id_returns_account() {
        Account actualAccount = sut.getAccountById(ACCOUNT_1.getAccountId());

        Assert.assertEquals(ACCOUNT_1, actualAccount);
    }

    @Test
    public void getAccountById_given_invalid_Id_returns_null() {
        Assert.assertNull(sut.getAccountById(10000));
    }
    @Test
    public void getAccountBalance_given_valid_Id_returns_balance() {
        BigDecimal actualBalance = sut.getAccountBalance(ACCOUNT_1.getAccountId());

        Assert.assertEquals(ACCOUNT_1.getBalance(), actualBalance);
    }
    @Test
    public void getAccountBalance_given_invalid_Id_returns_null() {
        Assert.assertNull(sut.getAccountBalance(10000));
    }
    @Test
    public void setAccountBalance_given_valid_Id_returns_newbalance() {
        Account updatedAccount = sut.setAccountBalance(ACCOUNT_1_1);

        Assert.assertEquals(ACCOUNT_1_1.getBalance(), updatedAccount.getBalance());
    }
    @Test
    public void setAccountBalance_given_invalid_Id_returns_null() {
        Account updatedAccount = sut.setAccountBalance(ACCOUNT_2_1);

        Assert.assertNull(updatedAccount);
    }

}
