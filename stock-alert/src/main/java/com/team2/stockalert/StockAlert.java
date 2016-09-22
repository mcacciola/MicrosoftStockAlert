package com.team2.stockalert;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class StockAlert {
    private boolean alarmSet;
    private Date dateTime;
    private StockQuote currentStockQuote;
    private Stock currentStock;

    public StockAlert() throws IOException {
        currentStock = YahooFinance.get("MSFT");
        currentStockQuote = currentStock.getQuote();
    }

    public String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("hh:mm");
        if (dateTime == null) {
            dateTime = new Date();
        }
        return df.format(dateTime);

    }

    public double getStockQuoteNow() throws IOException {
        double price = getCurrentStockPrice();
        setAlarmIfPercentDifferenceGreaterThan20(price);
        return price;
    }

    private double getCurrentStockPrice() {
        return currentStockQuote.getPrice().doubleValue();
    }

    private void setAlarmIfPercentDifferenceGreaterThan20(double price) throws IOException {
        if (getPercentageDifference(price, getStockQuoteYesterday()) > 20.0) {
            alarmSet = true;
        }
    }

    public double getStockQuoteYesterday() throws IOException {
        Calendar c1 = new GregorianCalendar();
        c1.add(Calendar.DAY_OF_YEAR, -1);


        List<HistoricalQuote>  quoteList = currentStock.getHistory(c1, c1);

        return quoteList.get(quoteList.size()-1).getClose().doubleValue();
    }

    public boolean isAlarmSet() {
        return alarmSet;
    }

    public void setTime(String newTime) {
        List<Integer> timeArray = getTimeArray(newTime);
        Calendar cal = setCalendarTime(timeArray);
        dateTime = cal.getTime();
    }

    private Calendar setCalendarTime(List<Integer> timeArray) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, timeArray.get(0));
        cal.set(Calendar.MINUTE, timeArray.get(1));
        return cal;
    }

    private List<Integer> getTimeArray(String newTime) {
        String[] splitTime = newTime.split(":");
        List<Integer> timeArray = new ArrayList<Integer>();
        populateTimeArray(splitTime, timeArray);
        return timeArray;
    }

    private void populateTimeArray(String[] splitTime, List<Integer> timeArray) {
        for (String timeInterval : splitTime) {
            timeArray.add(toInt(timeInterval));
        }
    }

    private int toInt(String interval) {
        return Integer.parseInt(interval);
    }

    public double getPercentageDifference(double stock1, double stock2) {
        return Math.abs(stock1 - stock2) / ( (stock1 + stock2) /2 ) * 100;
    }

    public void setCurrentStockPrice(double stockPrice) {
        currentStockQuote.setPrice(BigDecimal.valueOf(stockPrice));
    }
}
