package com.nutmeg.transactions;

import com.nutmeg.transactions.holding.Holding;
import com.nutmeg.transactions.holding.HoldingCalculatorImpl;
import com.nutmeg.transactions.transaction.TransactionModel;
import com.nutmeg.transactions.transaction.TxnType;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class NutmegTest {

    private List<TransactionModel> createTestData() {
        List<TransactionModel> holdingTransactions = new ArrayList<TransactionModel>();
        holdingTransactions.add(new TransactionModel("NEAA0000", LocalDate.of(2017, 01, 01), TxnType.DEP, 100, 1, "CASH"));
        holdingTransactions.add(new TransactionModel("NEAA0000", LocalDate.of(2017, 01, 02), TxnType.BOT, 20, 2.123, "VUKE"));
        holdingTransactions.add(new TransactionModel("NEAA0000", LocalDate.of(2017, 01, 02), TxnType.BOT, 30, 1.500, "VUSA"));
        holdingTransactions.add(new TransactionModel("NEAA0000", LocalDate.of(2017, 02, 01), TxnType.DIV, 0.2024, 1, "VUKE"));
        holdingTransactions.add(new TransactionModel("NEAA0000", LocalDate.of(2017, 02, 01), TxnType.SLD, 20, 2.000, "VUSA"));
        holdingTransactions.add(new TransactionModel("NEAA0000", LocalDate.of(2017, 01, 01), TxnType.BOT, 10.512, 3.3350, "GILS"));

        holdingTransactions.add(new TransactionModel("NEAB0001", LocalDate.of(2016, 12, 01), TxnType.DEP, 10000, 1, "CASH"));
        holdingTransactions.add(new TransactionModel("NEAB0001", LocalDate.of(2017, 03, 01), TxnType.WDR, 5000, 1, "CASH"));
        return holdingTransactions;
    }

    @Test
    public void nutmegScenarioParsingCsv() throws IOException{

        // before
        File file =  new File("nutmeg.csv");
        final LocalDate date = LocalDate.of(2017, 02, 01);
        HoldingCalculatorImpl holdingCalculator = new HoldingCalculatorImpl();

        // during
        Map<String, List<Holding>> holdingAccounts = holdingCalculator.calculateHoldings(file, date);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(17.6849)); // actual value is 17.68488 so rounding up is different from stated in test
        assertThat(holdingAccounts.get("NEAA0000").get(1).getAsset(), Is.is("VUKE"));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getHolding(), Is.is(20.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(2).getAsset(), Is.is("VUSA"));
        assertThat(holdingAccounts.get("NEAA0000").get(2).getHolding(), Is.is(10.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(3).getAsset(), Is.is("GILS"));
        assertThat(holdingAccounts.get("NEAA0000").get(3).getHolding(), Is.is(10.5120));

        assertThat(holdingAccounts.get("NEAB0001").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAB0001").get(0).getHolding(), Is.is(10000.0000));
    }

    @Test
    public void nutmegScenarioWithoutCsv() {

        // before
        final List<TransactionModel> holdingTransactions = createTestData();
        final LocalDate date = LocalDate.of(2017, 02, 01);
        HoldingCalculatorImpl holdingCalculator = new HoldingCalculatorImpl();

        // during
        Map<String, List<Holding>> holdingAccounts = holdingCalculator.calculateHoldings(holdingTransactions, date);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(17.6849)); // actual value is 17.68488 so rounding up is different from stated in test
        assertThat(holdingAccounts.get("NEAA0000").get(1).getAsset(), Is.is("VUKE"));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getHolding(), Is.is(20.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(2).getAsset(), Is.is("VUSA"));
        assertThat(holdingAccounts.get("NEAA0000").get(2).getHolding(), Is.is(10.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(3).getAsset(), Is.is("GILS"));
        assertThat(holdingAccounts.get("NEAA0000").get(3).getHolding(), Is.is(10.5120));

        assertThat(holdingAccounts.get("NEAB0001").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAB0001").get(0).getHolding(), Is.is(10000.0000));
    }

    @Test
    public void extraNutmegScenarioWithoutCsv() {

        // before
        final List<TransactionModel> holdingTransactions = createTestData();
        final LocalDate date = LocalDate.of(2017, 04, 01);
        HoldingCalculatorImpl holdingCalculator = new HoldingCalculatorImpl();

        // during
        Map<String, List<Holding>> holdingAccounts = holdingCalculator.calculateHoldings(holdingTransactions, date);

        // after
        assertNotNull(holdingAccounts);
        assertThat(holdingAccounts.get("NEAA0000").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAA0000").get(0).getHolding(), Is.is(17.6849)); // actual value is 17.68488 so rounding up is different from stated in test
        assertThat(holdingAccounts.get("NEAA0000").get(1).getAsset(), Is.is("VUKE"));
        assertThat(holdingAccounts.get("NEAA0000").get(1).getHolding(), Is.is(20.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(2).getAsset(), Is.is("VUSA"));
        assertThat(holdingAccounts.get("NEAA0000").get(2).getHolding(), Is.is(10.0000));
        assertThat(holdingAccounts.get("NEAA0000").get(3).getAsset(), Is.is("GILS"));
        assertThat(holdingAccounts.get("NEAA0000").get(3).getHolding(), Is.is(10.5120));

        assertThat(holdingAccounts.get("NEAB0001").get(0).getAsset(), Is.is("CASH"));
        assertThat(holdingAccounts.get("NEAB0001").get(0).getHolding(), Is.is(5000.0000));
    }
}
