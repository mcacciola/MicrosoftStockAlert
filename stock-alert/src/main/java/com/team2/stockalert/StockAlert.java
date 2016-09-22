package com.team2.stockalert;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by f563oyy on 9/20/2016.
 */
public class StockAlert {
    private static boolean alarmSet;
    private static Date dateTime;

    public static String getCurrentTime() {

        DateFormat df = new SimpleDateFormat("hh:mm");
        if (dateTime == null) {
            dateTime = new Date();
        }
        return df.format(dateTime);

    }

    public static double getStockQuote() throws IOException {
        Stock stock = YahooFinance.get("MSFT");
        double price = stock.getQuote().getPrice().doubleValue();
        if (stock.getQuote().getChangeInPercent().doubleValue() > 20) {
            alarmSet = true;
        }
        return price;
    }

    public static boolean isAlarmSet() {
        return alarmSet;
    }

    public static void setTime(String newTime) {
        List<Integer> timeArray = getTimeArray(newTime);
        Calendar cal = setCalendarTime(timeArray);
        dateTime = cal.getTime();
    }

    private static Calendar setCalendarTime(List<Integer> timeArray) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, timeArray.get(0));
        cal.set(Calendar.MINUTE, timeArray.get(1));
        return cal;
    }

    private static List<Integer> getTimeArray(String newTime) {
        String[] splitTime = newTime.split(":");
        List<Integer> timeArray = new ArrayList<Integer>();
        populateTimeArray(splitTime, timeArray);
        return timeArray;
    }

    private static void populateTimeArray(String[] splitTime, List<Integer> timeArray) {
        for (String timeInterval : splitTime) {
            timeArray.add(toInt(timeInterval));
        }
    }

    private static int toInt(String interval) {
        return Integer.parseInt(interval);
    }
}
