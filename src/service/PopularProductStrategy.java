package service;

import model.Product;
import model.User;

import java.util.List;

public class PopularProductStrategy implements RecommendationStrategy {
    @Override
    public List<Product> recommend(User user) {
        return List.of(new Product("iPhone", "Apple", 999.99));
    }
}