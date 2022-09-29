package com.product.service.services;

import com.product.service.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAll();
    Product get(UUID id);
    Product add(Product category);
    Product edit(UUID id, Product category);
    boolean delete(UUID id);
}
