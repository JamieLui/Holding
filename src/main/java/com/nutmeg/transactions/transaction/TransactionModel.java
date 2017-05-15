package com.nutmeg.transactions.transaction;


import java.time.LocalDate;

public class TransactionModel {

    private String account;
    private LocalDate transactionDate;
    private TxnType txnType;
    private double units;
    private double price;
    private String asset;

    public TransactionModel(){

    }

    public TransactionModel(final String account, final LocalDate transactionDate, final TxnType txnType, final double units, final double price, final String asset){
        setAccount(account);
        setTransactionDate(transactionDate);
        setTxnType(txnType);
        setUnits(units);
        setPrice(price);
        setAsset(asset);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnType txnType) {
        this.txnType = txnType;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }


}
