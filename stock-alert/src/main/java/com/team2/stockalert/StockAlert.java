package com.team2.stockalert;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockAlert {
    private boolean alarmSet;
    private Date dateTime;
    private StockQuote currentStockQuote;
    private Stock currentStock;
    private DateFormat dateFormat;

    public StockAlert() throws IOException {
        currentStock = YahooFinance.get("MSFT");
        currentStockQuote = currentStock.getQuote();
        dateTime = new Date();
    }

    public String getCurrentTime() {
        dateFormat = new SimpleDateFormat("hh:mm");
        return dateFormat.format(dateTime);

    }

    public String getStockQuoteNow() throws IOException {
        double price = getCurrentStockPrice();
        setAlarmIfPercentDifferenceLessThanNegative19(price);
        return getValueRounded2AsString(price);
    }

    private String getValueRounded2AsString(double value) {
        double roundedValue = roundDoubleToTwoDecimals(value);
        String[] valueArray = String.valueOf(roundedValue).split("\\.");
        return buildValueRounded2String(valueArray);
    }

    private String buildValueRounded2String(String[] valueArray) {
        return valueArray[0] + "." + buildValueAfterDecimalRounded2(valueArray[1]);
    }

    private String buildValueAfterDecimalRounded2(String number) {
        String postDecimalValue = number;
        if(isPostDecimalValueLength1(number)){
            postDecimalValue += "0";
        }
        return postDecimalValue;
    }

    private boolean isPostDecimalValueLength1(String centValue) {
        return centValue.length() == 1;
    }

    private double getCurrentStockPrice() {
        return roundDoubleToTwoDecimals(currentStockQuote.getPrice().doubleValue());
    }

    private void setAlarmIfPercentDifferenceLessThanNegative19(double price) throws IOException {
        if (getPercentageDifferenceAsDouble(price, getStockQuoteYesterday()) <= -20.0) {
            alarmSet = true;
        }
    }

    public double getStockQuoteYesterday() throws IOException {
        Calendar yesterday = getYesterday();
        List<HistoricalQuote>  yesterdaysQuoteList = getYesterdaysQuoteList(yesterday);
        return roundDoubleToTwoDecimals(getYesterdaysPrice(yesterdaysQuoteList));
    }

    private List<HistoricalQuote> getYesterdaysQuoteList(Calendar yesterday) throws IOException {
        return currentStock.getHistory(yesterday, yesterday);
    }

    private Calendar getYesterday() {
        Calendar c1 = new GregorianCalendar();
        c1.add(Calendar.DAY_OF_YEAR, -1);
        return c1;
    }

    private double getYesterdaysPrice(List<HistoricalQuote> quoteList) {
        return quoteList.get(quoteList.size() - 1).getClose().doubleValue();
    }

    public boolean isAlarmSet() {
        return alarmSet;
    }


    public void setTime(String newTime) {
        List<Integer> timeArray = getTimeArray(newTime);
        Calendar cal = setCalendarTime(timeArray);
        dateTime = cal.getTime();
    }

    public double getPercentageDifferenceAsDouble(double stock1, double stock2) {
        double percentDifference = (stock1 - stock2) / ( (stock1 + stock2) /2 ) * 100;
        return roundDoubleToTwoDecimals(percentDifference);

    }

    public double getPercentageDifferenceAsString(String value1, double value2) {
        double value = Double.parseDouble(value1);
        double percentDifference = (value - value2) / ( (value + value2) /2 ) * 100;
        return roundDoubleToTwoDecimals(percentDifference);

    }


    public void setCurrentStockPrice(double stockPrice) {
        currentStockQuote.setPrice(BigDecimal.valueOf(stockPrice));
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

    private double roundDoubleToTwoDecimals(double percentDifference) {
        return new BigDecimal(percentDifference).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
