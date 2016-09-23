<%@ page import="com.team2.stockalert.StockAlert" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%StockAlert stock = new StockAlert();

 String currentTime = stock.getCurrentTime();
 String stockQuote = stock.getStockQuoteNow();
 double percent = stock.getPercentageDifferenceAsString(stockQuote, stock.getStockQuoteYesterday());
 %>
<head>
    <title>This is a local liberty server</title>
  </head>
 <% if(!stock.isAlarmSet()) { %>
    <body style="background-color:white;">
    <embed src="money.mp3" autostart="true" loop="true" hidden="true">
 <% }else {%>
    <body style="background-color:red;">
    <embed src="alarm.mp3" autostart="true" loop="true" hidden="true">
<% } response.setIntHeader("Refresh", 300); %>


<table align="center" border="0">
    <tr>
        <th><b>Time</b></th>
        <th><b>MSFT Stock Value</b></th>
        <th><b>Difference</b></th>
    </tr>
    <tr>
        <td align="center"><% out.print(currentTime); %></td>
        <td align="center"><% out.print(stockQuote); %></td>
        <td align="center"><% out.print(percent + "%"); %></td>
    </tr>
</table>
</body>

</html>