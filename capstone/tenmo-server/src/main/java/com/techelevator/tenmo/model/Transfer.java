package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int id;
    private int transferTypeId;
    private int transferStatusId;
    private int accountFrom;
    private int accountTo;
    private BigDecimal amount;

    public int getId() {
        return id;
    }
    public int getTransferTypeId() {
        return transferTypeId;
    }
    public int getTransferStatusId() {
        return transferStatusId;
    }
    public int getAccountFrom() {
        return accountFrom;
    }
    public int getAccountTo() {
        return accountTo;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }
    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }
    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
