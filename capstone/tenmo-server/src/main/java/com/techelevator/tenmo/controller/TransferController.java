package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
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
    public Transfer getTransferById(@PathVariable int id) {
        return transferDao.getTransferById(id);
    }
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer requestTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
        return transferDao.requestTransfer(transferTypeId, transferStatusId, accountFrom, accountTo, amount);
    }
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.PUT)
    public Transfer updateTransfer(@Valid @RequestBody Transfer transfer, @PathVariable int id) {
        return transferDao.updateTransfer(transfer);
    }
//    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.PUT)
//    public Transfer sendTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
//        return transferDao.sendTransfer(transferTypeId, transferStatusId, accountFrom, accountTo, amount);
//    }
}
