package repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public InMemoryProductRepository() {
        products.add(new Product("iPhone 15", "Apple", 99990.00));
        products.add(new Product("Galaxy S24", "Samsung", 89990.00));
        products.add(new Product("MacBook Air", "Apple", 119990.00));
        products.add(new Product("Surface Laptop 5", "Microsoft", 105000.00));
        products.add(new Product("iPad Air", "Apple", 64990.00));
        products.add(new Product("AirPods Pro", "Apple", 24990.00));
        products.add(new Product("Xiaomi Redmi Note 13", "Xiaomi", 22990.00));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public List<Product> findByNameContaining(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByManufacturer(String manufacturer) {
        return products.stream()
                .filter(p -> p.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }
}