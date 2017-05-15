package com.nutmeg.transactions.holding.factory;

import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.transaction.TransactionModel;
import com.nutmeg.transactions.transaction.TxnType;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProcessDepositUpdateTest {

    private TransactionModel testTransation() {
        return new TransactionModel("NEAA0000", LocalDate.of(2017, 01, 01), TxnType.DEP, 100, 1, "CASH");
    }

    @Test
    public void testOneTransactionDepositEmptyHoldingAccounts() {

        // before
        Map<String, List<Holding>> holdingAccounts = new HashMap<String, List<Holding>>();
        final TransactionModel transaction = testTransation();
        ProcessDepositUpdate processDepositUpdate = new ProcessDepositUpdate();

        // during
        holdingAccounts = processDepositUpdate.updateHolding(holdingAccounts, transaction);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(100.0000));
    }

    @Test
    public void testTwoTransactionsDeposits() {

        // before
        Map<String, List<Holding>> holdingAccounts = new HashMap<String, List<Holding>>();
        final TransactionModel transaction = testTransation();
        ProcessDepositUpdate processDepositUpdate = new ProcessDepositUpdate();

        // during
        holdingAccounts = processDepositUpdate.updateHolding(holdingAccounts, transaction);
        holdingAccounts = processDepositUpdate.updateHolding(holdingAccounts, transaction);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(200.0000));
        assertThat(holdingAccounts.size(), Is.is(1));
        assertThat(holdingAccounts.get("NEAA0000").size(), Is.is(1));
    }
}