package com.nutmeg.transactions.csv;


import com.nutmeg.transactions.transaction.TransactionModel;
import com.nutmeg.transactions.transaction.TxnType;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParseCsv {

    public List<TransactionModel> processCsv(final File transactionFile) throws IOException {
        List<TransactionModel> transactions = new ArrayList<TransactionModel>();

        InputStream inputFS = new FileInputStream(transactionFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
        transactions = br.lines().map(mapToTransactionModel).collect(Collectors.toList());
        br.close();

        return transactions;
    }

    private Function<String, TransactionModel> mapToTransactionModel = (line) -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String[] p = line.split(",");
        TransactionModel transactionModel = new TransactionModel();

        transactionModel.setAccount(p[0]);
        transactionModel.setTransactionDate(LocalDate.parse(p[1], formatter));
        transactionModel.setTxnType(TxnType.valueOf(p[2]));
        transactionModel.setUnits(Double.valueOf(p[3]));
        transactionModel.setPrice(Double.valueOf(p[4]));
        transactionModel.setAsset(p[5]);

        return transactionModel;
    };
}
