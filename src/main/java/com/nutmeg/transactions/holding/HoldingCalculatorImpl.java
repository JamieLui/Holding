package com.nutmeg.transactions.holding;

import com.nutmeg.transactions.csv.ParseCsv;
import com.nutmeg.transactions.holding.factory.UpdateHoldingFactory;
import com.nutmeg.transactions.transaction.TransactionModel;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HoldingCalculatorImpl implements HoldingCalculator{

    @Override
    public Map<String, List<Holding>> calculateHoldings(final File transactionFile, final LocalDate date) throws IOException {
        Map<String, List<Holding>> holdingAccounts = new HashMap<>();
        ParseCsv parseCsv = new ParseCsv();

        List<TransactionModel> holdingTransactions = parseCsv.processCsv(transactionFile);

        List<TransactionModel> filteredHoldingTransactions = filterHoldingTransactionsByDate(holdingTransactions, date);

        holdingAccounts = calculateHoldingAccountsFromTransactions(holdingAccounts, filteredHoldingTransactions);

        return holdingAccounts;
    }

    /* Without CSV */
    @Override
    public Map<String, List<Holding>> calculateHoldings(final List<TransactionModel> holdingTransactions, final LocalDate date) {

        Map<String, List<Holding>> holdingAccounts = new HashMap<>();

        List<TransactionModel> filteredHoldingTransactions = filterHoldingTransactionsByDate(holdingTransactions, date);
        holdingAccounts = calculateHoldingAccountsFromTransactions(holdingAccounts, filteredHoldingTransactions);

        return holdingAccounts;
    }

    private List<TransactionModel> filterHoldingTransactionsByDate(final List<TransactionModel> holdingTransactions, final LocalDate date) {
        return holdingTransactions.stream().filter(transaction -> transaction.getTransactionDate().isBefore(date.plusDays(1))).collect(Collectors.toList());
    }

    private Map<String, List<Holding>> calculateHoldingAccountsFromTransactions(Map<String, List<Holding>> holdingAccounts, final List<TransactionModel> holdingTransactions) {
        Supplier<UpdateHoldingFactory> holdingFactory =  UpdateHoldingFactory::new;

        for (TransactionModel transaction : holdingTransactions) {
            holdingAccounts = holdingFactory.get().calculateHolding(transaction.getTxnType()).updateHolding(holdingAccounts, transaction);
        }

        return holdingAccounts;
    }
}

