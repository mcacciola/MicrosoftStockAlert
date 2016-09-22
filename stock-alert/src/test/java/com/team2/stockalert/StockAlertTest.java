package com.team2.stockalert;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StockAlertTest {
    @Test
    public void canCreateStockAlert(){
        assertEquals(StockAlert.class, new StockAlert().getClass());
    }

    @Test
    public void stockQuoteReturnsStockValue() throws IOException {
        assertNotEquals(0.00, StockAlert.getStockQuoteNow());
    }

    @Test
    public void testGetCurrentTimeReturnsTime() {
        StockAlert.setTime("12:01");
        assertEquals("12:01", StockAlert.getCurrentTime());
    }

    @Test
    public void percentChangeCalculationTest() throws IOException {
        assertEquals(40.0, StockAlert.getPercentageDifference(50.0, 75.0), .1);
    }

    @Test
    public void stockAlertsTraderIfBelow20Percent(){
        assertTrue(StockAlert.isAlarmSet());
    }


}