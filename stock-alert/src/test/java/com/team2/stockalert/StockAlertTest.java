package com.team2.stockalert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StockAlertTest {

    private StockAlert stockAlert;

    @Before
    public void setup() throws IOException {
        stockAlert = new StockAlert();
    }

    @Test
    public void canCreateStockAlert() throws IOException {
        assertEquals(StockAlert.class, new StockAlert().getClass());
    }

    @Test
    public void stockQuoteReturnsStockValue() throws IOException {
        assertNotEquals(0.00, stockAlert.getStockQuoteNow());
    }

    @Test
    public void testGetCurrentTimeReturnsTime() {
        stockAlert.setTime("12:01");
        assertEquals("12:01", stockAlert.getCurrentTime());
    }

    @Test
    public void negativePercentChangeCalculationTest() throws IOException {
        assertEquals(-40.0, stockAlert.getPercentageDifference(50.0, 75.0), .1);
    }

    @Test
    public void positivePercentChangeCalculationTest() throws IOException {
        assertEquals(40.0, stockAlert.getPercentageDifference(75.0, 50.0), .1);
    }

    @Test
    public void stockDoesNotAlertTraderIfAboveNegative20Percent() throws IOException {
        stockAlert.setCurrentStockPrice(50.00);
        stockAlert.getStockQuoteNow();
        assertFalse(stockAlert.isAlarmSet());
    }

    @Test
    public void stockAlertsTraderIfBelowNegative20Percent() throws IOException {
        stockAlert.setCurrentStockPrice(0.00);
        stockAlert.getStockQuoteNow();
        assertTrue(stockAlert.isAlarmSet());
    }
}