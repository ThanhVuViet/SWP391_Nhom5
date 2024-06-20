/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Order;
import Entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class OrderDao extends MyDAO {

    public List<Order> getOrder(Date startDay, Date endDay) {
        String query = "SELECT * \n"
                + "FROM Orders \n"
                + "WHERE order_date BETWEEN ? AND ?;";
        List<Order> orderList = new ArrayList<>();
        try {

            ps = con.prepareStatement(query);
            ps.setDate(1, (java.sql.Date) startDay);
            ps.setDate(2, (java.sql.Date) endDay);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                Date orderTime = rs.getDate("order_date");
                double total = rs.getDouble("total_amount");
                Order order = new Order(orderId, userId, (java.sql.Date) orderTime, total);
                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return orderList;
    }

    public List<Order> getOrders() {
        String query = "SELECT * \n"
                + "FROM Orders \n";

        List<Order> orderList = new ArrayList<>();
        try {

            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                Date orderTime = rs.getDate("order_date");
                double total = rs.getDouble("total_amount");
                Order order = new Order(orderId, userId, (java.sql.Date) orderTime, total);
                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }

        return orderList;
    }

    public Map<Integer, Double> getMonthlyDouble(int year) {
        String query = "SELECT Month(o.order_date) as month, sum(o.total_amount) as monthly\n"
                + "FROM Orders o\n"
                + "WHERE Year(o.order_date) = ?\n"
                + "GROUP BY MONTH(o.order_date);";
        Map<Integer, Double> monthlyRevenue = new HashMap<>();
        try {

            ps = con.prepareStatement(query);
            ps.setInt(1, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                double total = rs.getDouble("monthly");
                monthlyRevenue.put(month, total);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return monthlyRevenue;
    }
    
}
