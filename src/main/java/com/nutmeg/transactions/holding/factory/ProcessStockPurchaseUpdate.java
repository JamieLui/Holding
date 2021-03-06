package com.nutmeg.transactions.holding.factory;


import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ProcessStockPurchaseUpdate implements UpdateHolding {

    @Override
    public Map<String,List<Holding>> updateHolding(final Map<String,List<Holding>> holdingAccounts, final TransactionModel transaction) {

        DecimalFormat df = DecimalFormatUtils.decimalFormat();
        List<Holding> holdingList = holdingAccounts.get(transaction.getAccount());

        updateOrCreateAsset(holdingAccounts, transaction, df, holdingList);
        updateCashHolding(transaction, df, holdingList);

        return holdingAccounts;
    }

    private void updateCashHolding(final TransactionModel transaction, final DecimalFormat df, final List<Holding> holdingList) {
        holdingList.stream().filter(holding -> holding.getAsset().equals("CASH")).forEach(holding ->
            holding.setHoldings(
                Double.valueOf(df.format(holding.getHolding() - (transaction.getUnits() * transaction.getPrice())))
            )
        );
    }

    private void updateOrCreateAsset(final Map<String, List<Holding>> holdingAccounts, final TransactionModel transaction, final DecimalFormat df, final List<Holding> holdingList) {
        Holding findHolding = holdingList.stream().filter(holding -> holding.getAsset().equals(transaction.getAsset())).findFirst().orElse(null);

        if (findHolding != null) {
            holdingList.stream().filter(holding -> holding.getAsset().equals(transaction.getAsset())).forEach(holding ->
                holding.setHoldings(
                    Double.valueOf(df.format(holding.getHolding() + transaction.getUnits()))
                )
            );
        } else {
            Holding newHolding = new Holding();
            newHolding.setAsset(transaction.getAsset());
            newHolding.setHoldings(Double.valueOf(df.format(transaction.getUnits())));
            holdingList.add(newHolding);
            holdingAccounts.put(transaction.getAccount(), holdingList);
        }
    }
}
