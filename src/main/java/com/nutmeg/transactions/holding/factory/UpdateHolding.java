package com.nutmeg.transactions.holding.factory;

import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;

import java.util.List;
import java.util.Map;

public interface UpdateHolding {
    Map<String,List<Holding>> updateHolding(final Map<String,List<Holding>> holdingAccounts, final TransactionModel holdingTransaction);
}
