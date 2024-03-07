package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcUserDao;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTransferDaoTests  extends BaseDaoTests {
    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

}
