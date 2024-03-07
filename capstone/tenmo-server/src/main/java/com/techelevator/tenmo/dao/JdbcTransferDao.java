package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private final JdbcTemplate jdbcTemplate;
    private static List<Transfer> reservations = new ArrayList<>();

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int id) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfer;
    }

    @Override
    public Transfer requestTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES(" +
                "(SELECT transfer_type_id FROM transfer_type WHERE transfer_type_id = ?)," +
                "(SELECT transfer_status_id FROM transfer_status WHERE transfer_status_id = ?)," +
                "account_from = ?, account_to = ?, amount = ?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferTypeId, transferStatusId, accountFrom, accountTo, amount);
            if (results.next()) {
                newTransfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newTransfer;
    }
    @Override
    public Transfer sendTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
        Transfer sentTransfer = null;
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                "VALUES(" +
                "(SELECT transfer_type_id FROM transfer_type WHERE transfer_type_id = ?)," +
                "(SELECT transfer_status_id FROM transfer_status WHERE transfer_status_id = ?)," +
                "account_from = ?, account_to = ?, amount = ?);";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferTypeId, transferStatusId, accountFrom, accountTo, amount);
            if (results.next()) {
                sentTransfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sentTransfer;
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        Transfer updatedTransfer;

        String sql = "UPDATE transfer " +
                "SET transfer_type_id = ?, transfer_status_id = ?, account_from = ?, account_to = ?, amount = ? " +
                "WHERE transfer_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, transfer.getTransferTypeId(), transfer.getTransferStatusId(),
                    transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount(), transfer.getId());

            if (rowsAffected == 0) {
                updatedTransfer = null;
//                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedTransfer = getTransferById(transfer.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedTransfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }
}
