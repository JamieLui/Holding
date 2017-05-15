package com.nutmeg.transactions.holding.factory;


import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ProcessStockSaleUpdate implements UpdateHolding {

    @Override
    public Map<String,List<Holding>> updateHolding(final Map<String,List<Holding>> holdingAccounts, final TransactionModel transaction) {

        DecimalFormat df = DecimalFormatUtils.decimalFormat();

        List<Holding> holdingList = holdingAccounts.get(transaction.getAccount());

        reduceAssetUnits(transaction, df, holdingList);

        increaseCashHolding(transaction, df, holdingList);

        return holdingAccounts;
    }

    private void reduceAssetUnits(final TransactionModel transaction, final DecimalFormat df, final List<Holding> holdingList) {
        holdingList.stream().filter(holding -> holding.getAsset().equals(transaction.getAsset())).forEach(holding ->
            holding.setHoldings(
                Double.valueOf(df.format(holding.getHolding() - transaction.getUnits()))
            )
        );
    }

    private void increaseCashHolding(final TransactionModel transaction, final DecimalFormat df, final List<Holding> holdingList) {
        holdingList.stream().filter(holding -> holding.getAsset().equals("CASH")).forEach(holding ->
                holding.setHoldings(
                    Double.valueOf(df.format(holding.getHolding() + (transaction.getUnits() * transaction.getPrice()))
                )
            )
        );
    }
}
