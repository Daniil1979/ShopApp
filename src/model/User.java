package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Order> orderHistory;

    public User(String name) {
        this.name = name;
        this.orderHistory = new ArrayList<>();
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}