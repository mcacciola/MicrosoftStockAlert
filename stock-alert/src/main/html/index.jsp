<%@ page import="com.team2.stockalert.StockAlert" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is a local liberty server</title>
  </head>
<body>
<% response.setIntHeader("Refresh", 300); %>

<table border="0">
    <tr>
        <th><b>Time</b></th>
        <th><b>MSFT Stock Value</b></th>
        <th><b>Difference</b></th>
    </tr>
    <tr>
        <td><% out.print(StockAlert.getCurrentTime()); %></td>
        <td><% out.print(StockAlert.getStockQuoteNow()); %></td>
        <td>0.00%</td>
    </tr>
</table>
</body>

</html>