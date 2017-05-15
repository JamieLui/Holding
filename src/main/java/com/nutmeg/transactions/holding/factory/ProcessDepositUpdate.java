package com.nutmeg.transactions.holding.factory;


import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessDepositUpdate implements UpdateHolding {

    @Override
    public Map<String,List<Holding>> updateHolding(final Map<String, List<Holding>> holdingAccounts, final TransactionModel transaction) {

        DecimalFormat df = DecimalFormatUtils.decimalFormat();

        List<Holding> holdingList = holdingAccounts.get(transaction.getAccount());

        if (holdingList == null) {
            holdingList = createCashHolding(holdingAccounts, transaction, df);
            holdingAccounts.put(transaction.getAccount(), holdingList);
        } else {
            addCashHolding(transaction, holdingList, df);
        }

        return holdingAccounts;
    }

    private void addCashHolding(final TransactionModel transaction, final List<Holding> holdingList, final DecimalFormat df) {
        holdingList.stream().filter(holding -> holding.getAsset().equals("CASH")).forEach(holding ->
            holding.setHoldings(
                Double.valueOf(df.format(holding.getHolding() + transaction.getUnits()))
            )
        );
    }

    private List<Holding> createCashHolding(Map<String, List<Holding>> holdingAccounts, TransactionModel transaction, DecimalFormat df) {
        List<Holding> holdingList;
        holdingList = new ArrayList<Holding>();

        Holding holding = new Holding();
        holding.setAsset(transaction.getAsset());
        holding.setHoldings(Double.valueOf(df.format(transaction.getUnits())));
        holdingList.add(holding);

        holdingAccounts.put(transaction.getAccount(), holdingList);
        return holdingList;
    }
}
