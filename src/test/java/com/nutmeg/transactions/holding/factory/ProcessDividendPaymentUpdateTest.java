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
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProcessDividendPaymentUpdateTest {

    @Test
    public void testProcessDividendOneTransactionEmptyHoldingAccounts() {

        // before
        Map<String, List<Holding>> holdingAccounts = TestData.setUpCashHolding();
        final TransactionModel transaction = new TransactionModel("NEAA0000", LocalDate.of(2017, 02, 01), TxnType.DIV, 10, 1, "VUKE");
        ProcessDividendPaymentUpdate processDividendPaymentUpdate = new ProcessDividendPaymentUpdate();

        // during
        holdingAccounts = processDividendPaymentUpdate.updateHolding(holdingAccounts, transaction);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(110.0000));
    }

}