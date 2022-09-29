package com.product.service.services;

import com.product.service.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category get(UUID id);
    Category add(Category category);
    Category edit(UUID id, Category category);
    boolean delete(UUID id);
}
