package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    List<Transfer> getTransfers();
    Transfer getTransferById(int id);
    Transfer requestTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount);
    Transfer updateTransfer(Transfer transfer);
}