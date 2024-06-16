<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat, java.text.ParseException, java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Date Parameters</title>
</head>
<body>
    <h2>Test Date Parameters</h2>
    <form action="OrderFilter" method="post">
        <label for="startDate">Start Date:</label>
        <input type="text" id="startDate" name="startDate" placeholder="YYYY-MM-DD"><br><br>
        <label for="endDate">End Date:</label>
        <input type="text" id="endDate" name="endDate" placeholder="YYYY-MM-DD"><br><br>
        <input type="submit" value="Submit">
    </form>

    <h2>Results</h2>
    <%
        // Lấy danh sách order từ attribute
        List<Order> orderList = (List<Order>) request.getAttribute("orderList");

        // Hiển thị lỗi nếu có
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<p>Error: " + error + "</p>");
        }

        // Hiển thị danh sách order nếu không null
        if (orderList != null) {
            out.println("<table border='1'>");
            out.println("<tr><th>Order ID</th><th>Order Date</th><th>Order Details</th></tr>");
            for (Order order : orderList) {
                out.println("<tr>");
                out.println("<td>" + order.getOrderId() + "</td>");
                out.println("<td>" + order.getOrderDate() + "</td>");
               
                out.println("</tr>");
            }
            out.println("</table>");
        } else if (error == null) {
            out.println("<p>No orders found for the specified date range.</p>");
        }

        // Hiển thị thông tin ngày bắt đầu và kết thúc
        String startDay = (String) request.getAttribute("startDay");
        String endDay = (String) request.getAttribute("endDay");
        int count = (int) request.getAttribute("count");
        if (startDay != null && endDay != null) {
            out.println("<p>Start Date: " + startDay + "</p>");
            out.println("<p>End Date: " + endDay + "</p>");
            out.println("<p>count: " + count + "</p>");
        } else {
            out.println("<p>Start Date or End Date is null</p>");
        }
    %>
</body>
</html>
