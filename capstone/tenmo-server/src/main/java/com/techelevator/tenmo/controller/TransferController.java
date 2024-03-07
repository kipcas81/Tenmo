package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

public class TransferController {
    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfers() {
        return transferDao.getTransfers();
    }
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransfersById(int id) {
        return transferDao.getTransferById(id);
    }
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer requestTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
        return transferDao.requestTransfer(transferTypeId, transferStatusId, accountFrom, accountTo, amount);
    }
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.PUT)
    public Transfer updateTransfer(Transfer transfer) {
        return transferDao.updateTransfer(transfer);
    }
}
