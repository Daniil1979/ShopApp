package model;

import java.time.LocalDateTime;

public class Order {
    private static int idCounter = 1;
    private final int id;
    private final User user;
    private final Cart cart;
    private LocalDateTime orderDate;
    private DeliveryStatus status;

    public Order(User user, Cart cart) {
        this.id = idCounter++;
        this.user = user;
        this.cart = cart;
        this.orderDate = LocalDateTime.now();
        this.status = DeliveryStatus.PROCESSING;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() {
        return cart;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Заказ №%d от %s: %s", id, orderDate.toLocalDate(), status);
    }
}