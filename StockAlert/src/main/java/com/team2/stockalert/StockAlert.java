package com.team2.stockalert;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by f563oyy on 9/20/2016.
 */
public class StockAlert {
    private static boolean alarmSet;

    public static BigDecimal getStockQuote() throws IOException {
        Stock stock = YahooFinance.get("MSFT");
        BigDecimal price = stock.getQuote().getPrice();
        return price;
    }
}
