package repository;

import model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    List<Product> findByNameContaining(String keyword);

    List<Product> findByPriceRange(double min, double max);

    List<Product> findByManufacturer(String manufacturer);
}