package service;

import model.Product;
import model.User;

import java.util.List;

public class RecommendationService {
    private RecommendationStrategy strategy = new PopularProductStrategy();

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Product> getRecommendations(User user) {
        return strategy.recommend(user);
    }
}