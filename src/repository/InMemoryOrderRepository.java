package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findByUser(Object user) {
        return orders.stream()
                .filter(o -> o.getUser().equals(user))
                .collect(Collectors.toList());
    }
}