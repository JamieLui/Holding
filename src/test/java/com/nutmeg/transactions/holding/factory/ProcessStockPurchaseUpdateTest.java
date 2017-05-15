package com.nutmeg.transactions.holding.factory;

import com.nutmeg.transactions.TestData;
import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;
import com.nutmeg.transactions.transaction.TxnType;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProcessStockPurchaseUpdateTest {

    private TransactionModel testTransation() {
        return new TransactionModel("NEAA0000", LocalDate.of(2017, 01, 02), TxnType.BOT, 20, 2, "VUKE");
    }

    @Test
    public void testProcessStockPurchaseOneTransactionEmptyHoldingAccounts() {

        // before
        Map<String, List<Holding>> holdingAccounts = TestData.setUpCashHolding();
        final TransactionModel transaction = testTransation();
        ProcessStockPurchaseUpdate processStockPurchaseUpdate = new ProcessStockPurchaseUpdate();

        // during
        holdingAccounts = processStockPurchaseUpdate.updateHolding(holdingAccounts, transaction);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(60.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getAsset(), Is.is("VUKE"));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getHolding(), Is.is(20.0000));
    }

    @Test
    public void testProcessStockPurchaseTwoTransactionsPreExistingHoldingAccounts() {

        // before
        Map<String, List<Holding>> holdingAccounts = TestData.setUpCashHoldingWithVuke();
        final TransactionModel transaction = testTransation();
        ProcessStockPurchaseUpdate processStockPurchaseUpdate = new ProcessStockPurchaseUpdate();

        // during
        holdingAccounts = processStockPurchaseUpdate.updateHolding(holdingAccounts, transaction);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(60.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getAsset(), Is.is("VUKE"));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getHolding(), Is.is(40.0000));
    }
}