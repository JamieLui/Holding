package com.nutmeg.transactions.holding;

import com.nutmeg.transactions.transaction.TransactionModel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HoldingCalculator {
    Map<String,List<Holding>> calculateHoldings(final File transactionFile, final LocalDate date) throws IOException;

    Map<String,List<Holding>> calculateHoldings(final List<TransactionModel> holdingTransactions, final LocalDate date);
}

