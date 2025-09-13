package model;

public class Product {
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;

    private String name;
    private String manufacturer;
    private double price;
    private int rating;

    public Product(String name, String manufacturer, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.rating = 0;
    }

    public void setRating(int rating) {
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("Рейтинг должен быть от " + MIN_RATING + " до " + MAX_RATING);
        }
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f руб. [Рейтинг: %d]", name, manufacturer, price, rating);
    }
}