package com.team2.stockalert;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StockAlertTest {

    @Test
    public void canCreateStockAlert(){
        assertEquals(StockAlert.class, new StockAlert().getClass());
    }

    @Test
    public void stockQuoteReturnsStockValue() throws IOException {
        assertNotEquals(0.00, StockAlert.getStockQuote());
    }


    @Test
    public void stockAlertsTraderIfBelow20Percent(){
        assertTrue(StockAlert.isAlarmSet());
    }

}