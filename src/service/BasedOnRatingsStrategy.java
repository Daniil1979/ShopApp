package service;

import model.Product;
import model.User;

import java.util.List;

public class BasedOnRatingsStrategy implements RecommendationStrategy {
    @Override
    public List<Product> recommend(User user) {
        return List.of(new Product("MacBook", "Apple", 1499.99));
    }
}