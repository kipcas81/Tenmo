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
//    STARTING_BALANCE.setScale(2);
    private static final Account ACCOUNT_1 = new Account(2001, 1001, STARTING_BALANCE);
    private static final Account ACCOUNT_2 = new Account(2002, 1002, STARTING_BALANCE);
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

}
