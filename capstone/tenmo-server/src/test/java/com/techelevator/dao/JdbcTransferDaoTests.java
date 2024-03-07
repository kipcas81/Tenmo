package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionSuspensionNotSupportedException;

import java.math.BigDecimal;

public class JdbcTransferDaoTests  extends BaseDaoTests {
    private static final BigDecimal TRANSFER_AMOUNT1 = new BigDecimal("100.00");
    private static final BigDecimal TRANSFER_AMOUNT2 = new BigDecimal("200.00");
    private static final BigDecimal TRANSFER_AMOUNT3 = new BigDecimal("300.00");
    private static final BigDecimal TRANSFER_AMOUNT4 = new BigDecimal("400.00");
    private static final BigDecimal TRANSFER_AMOUNT5 = new BigDecimal("500.00");
    private static final BigDecimal TRANSFER_AMOUNT6 = new BigDecimal("600.00");
    private static final BigDecimal TRANSFER_AMOUNT7 = new BigDecimal("700.00");
    private static final BigDecimal TRANSFER_AMOUNT8 = new BigDecimal("800.00");
    //    STARTING_BALANCE.setScale(2);

    private static final Transfer TRANSFER_1 = new Transfer(3001,1,1,2001, 2002, TRANSFER_AMOUNT1);
    private static final Transfer TRANSFER_1_1 = new Transfer(3001,1,1,2001, 2002, TRANSFER_AMOUNT7);
    private static final Transfer TRANSFER_2 = new Transfer(3001,1,2,2002, 2003, TRANSFER_AMOUNT2);
    private static final Transfer TRANSFER_2_1 = new Transfer(4001,2,1,2001, 2002, TRANSFER_AMOUNT8);
    private static final Transfer TRANSFER_3 = new Transfer(3003,1,3,2001, 2003, TRANSFER_AMOUNT3);
    private static final Transfer TRANSFER_4 = new Transfer(3004,2,1,2003, 2001, TRANSFER_AMOUNT4);
    private static final Transfer TRANSFER_5 = new Transfer(3005,2,2,2002, 2001, TRANSFER_AMOUNT5);
    private static final Transfer TRANSFER_6 = new Transfer(3006,2,3,2003, 2002, TRANSFER_AMOUNT6);

    private JdbcTransferDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);
    }

    @Test
    public void getTransferById_given_valid_Id_returns_transfer() {
        Transfer actualTransfer = sut.getTransferById(TRANSFER_1.getId());

        Assert.assertEquals(TRANSFER_1, actualTransfer);
    }
    @Test
    public void getTransferById_given_invalid_Id_returns_null() {
        Assert.assertNull(sut.getTransferById(10000));
    }
    @Test
    public void updateTransfer_given_valid_Id_returns_transfer() {
        Transfer updatedTransfer = sut.updateTransfer(TRANSFER_1_1);

        Assert.assertEquals(TRANSFER_1_1, updatedTransfer);
    }
    @Test
    public void updateTransfer_given_invalid_Id_returns_null() {
        Transfer updatedTransfer = sut.updateTransfer(TRANSFER_2_1);

        Assert.assertNull(updatedTransfer);
    }

}
