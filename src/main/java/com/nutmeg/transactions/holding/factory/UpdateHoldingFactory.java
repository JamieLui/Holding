package com.nutmeg.transactions.holding.factory;

import com.nutmeg.transactions.transaction.TxnType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class UpdateHoldingFactory {

    final static Map<TxnType, Supplier<UpdateHolding>> map = new HashMap<>();

    static {
        map.put(TxnType.BOT, ProcessStockPurchaseUpdate::new);
        map.put(TxnType.DEP, ProcessDepositUpdate::new);
        map.put(TxnType.DIV, ProcessDividendPaymentUpdate::new);
        map.put(TxnType.SLD, ProcessStockSaleUpdate::new);
        map.put(TxnType.WDR, ProcessWithdrawlUpdate::new);
    }

    public UpdateHolding calculateHolding(TxnType txnType) throws IllegalArgumentException{
        Supplier<UpdateHolding> calculateHoldingSupplier = map.get(txnType);

        if (calculateHoldingSupplier != null) {
            return calculateHoldingSupplier.get();
        }

        throw new IllegalArgumentException("No implementation for txnType: " + txnType);
    }

}
