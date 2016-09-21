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
        assertNotEquals(0.00, StockAlert.getStockQuote());
    }

    @Test
    public void stockDontAlertsTraderIfAbove20Percent(){
        assertFalse(StockAlert.isAlarmSet());
    }

    @Test
    public void time(){
        System.out.println(StockAlert.time());
    }

//    @Test
//    public void stockAlertsTraderIfBelow20Percent(){
//        assertTrue(StockAlert.isAlarmSet());
//    }

}