package com.team2.stockalert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockAlertTest {

    @Test
    public void canCreateStockAlert(){
        assertEquals(StockAlert.class, new StockAlert().getClass());
    }

    @Test
    public void stockQuoteIs53(){
        assertEquals(53, StockAlert.getStockQuote());
    }

}