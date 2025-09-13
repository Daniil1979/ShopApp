package service;

import model.Product;
import model.User;

import java.util.List;

public interface RecommendationStrategy {
    List<Product> recommend(User user);
}