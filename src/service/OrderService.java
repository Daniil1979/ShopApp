package service;

import model.Order;
import model.User;
import repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(User user, model.Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Корзина пуста");
        }
        Order order = new Order(user, cart);
        orderRepository.save(order);
        user.addOrderToHistory(order);
        return order;
    }

    public void trackOrder(Order order) {
        System.out.println("Статус заказа: " + order.getStatus());
    }

    public void returnOrder(Order order) {
        if (order.getStatus() == model.DeliveryStatus.DELIVERED) {
            order.setStatus(model.DeliveryStatus.RETURNED);
            System.out.println("Заказ возвращён.");
        } else {
            System.out.println("Возврат невозможен. Статус: " + order.getStatus());
        }
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}