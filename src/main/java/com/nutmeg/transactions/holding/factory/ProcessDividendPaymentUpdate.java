package com.nutmeg.transactions.holding.factory;


import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ProcessDividendPaymentUpdate implements UpdateHolding {

    @Override
    public Map<String,List<Holding>> updateHolding(final Map<String,List<Holding>> holdingAccounts, final TransactionModel transaction) {
        DecimalFormat df = DecimalFormatUtils.decimalFormat();
        List<Holding> holdingList = holdingAccounts.get(transaction.getAccount());

        addDividentToCashHolding(transaction, df, holdingList);

        return holdingAccounts;
    }

    private void addDividentToCashHolding(final TransactionModel transaction, final DecimalFormat df, final List<Holding> holdingList) {
        holdingList.stream().filter(holding -> holding.getAsset().equals("CASH")).forEach( holding ->
            holding.setHoldings(
                Double.valueOf(
                    df.format(holding.getHolding() + (transaction.getUnits() * transaction.getPrice()))
                )
            )
        );
    }
}
