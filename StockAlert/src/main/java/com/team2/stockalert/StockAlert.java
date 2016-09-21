package com.team2.stockalert;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

/**
 * Created by f563oyy on 9/20/2016.
 */
public class StockAlert {
    private static boolean alarmSet;

    public static double getStockQuote() throws IOException {
        Stock stock = YahooFinance.get("MSFT");
        double price = stock.getQuote().getPrice().doubleValue();
        if(stock.getQuote().getChangeInPercent().doubleValue() > 20){
            alarmSet = true;
        }
        return price;
    }

    public static boolean isAlarmSet() {
        return alarmSet;
    }
}
