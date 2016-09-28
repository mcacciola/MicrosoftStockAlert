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
        stockAlert.setCurrentStockPrice(50.01);
        assertEquals("50.01", stockAlert.getStockQuoteNow());
    }

    @Test
    public void stockQuoteReturns0dot00IfStockValueIs0dot00() throws IOException {
        stockAlert.setCurrentStockPrice(0.00);
        assertEquals("0.00", stockAlert.getStockQuoteNow());
    }

    @Test
    public void testGetCurrentTimeReturnsTime() {
        stockAlert.setTime("12:01");
        assertEquals("12:01", stockAlert.getCurrentTime());
    }

    @Test
    public void negativePercentChangeCalculationTest() throws IOException {
        assertEquals(-40.0, stockAlert.getPercentageDifferenceAsDouble(50.00, 75.0), .1);
    }

    @Test
    public void positivePercentChangeCalculationTest() throws IOException {
        assertEquals(40.0, stockAlert.getPercentageDifferenceAsDouble(75.00, 50.0), .1);
    }

    @Test
    public void negativePercentChangeWithStringTest() throws IOException {
        assertEquals(-40.0, stockAlert.getPercentageDifferenceAsString("50.00", 75.0), .1);
    }

    @Test
    public void positivePercentChangeWithStringTest() throws IOException {
        assertEquals(40.0, stockAlert.getPercentageDifferenceAsString("75.00", 50.0), .1);
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