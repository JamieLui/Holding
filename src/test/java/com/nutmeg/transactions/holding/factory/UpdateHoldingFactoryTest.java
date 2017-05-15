package com.nutmeg.transactions.holding.factory;

import com.nutmeg.transactions.transaction.TxnType;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UpdateHoldingFactoryTest {

    @Test
    public void testFactoryReturnsValue() {

        // before
        TxnType txnType = TxnType.DEP;
        UpdateHoldingFactory processWithdrawlUpdate = new UpdateHoldingFactory();

        // during
        UpdateHolding updateHolding = processWithdrawlUpdate.calculateHolding(txnType);

        // after
        assertNotNull(updateHolding);
    }

    @Test
    public void testFactoryErrorMessage() {

        // before
        TxnType txnType = null;
        UpdateHoldingFactory processWithdrawlUpdate = new UpdateHoldingFactory();
        IllegalArgumentException exception = new IllegalArgumentException();

        // during
        try {
            processWithdrawlUpdate.calculateHolding(txnType);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        // after
        assertThat(exception.getMessage(), Is.is("No implementation for txnType: " + txnType));
    }
}