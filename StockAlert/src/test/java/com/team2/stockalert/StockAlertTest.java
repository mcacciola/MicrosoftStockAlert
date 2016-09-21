package com.team2.stockalert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StockAlertTest {

    @Test
    public void canCreateStockAlert(){
        assertEquals(StockAlert.class, new StockAlert().getClass());
    }

    @Test
    public void stockQuoteReturnsStockValue(){
        assertNotEquals(0, StockAlert.getStockQuote());
    }

}