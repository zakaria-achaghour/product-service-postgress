package com.product.service.services.impl;

import com.product.service.model.Category;
import com.product.service.repostitories.CategoryRepository;
import com.product.service.request.CategoryRequest;
import com.product.service.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(c -> {
            if (c.getDeletedAt() == null) {
                categories.add(c);
            }
        });
        return categories;
    }

    @Override
    public Category get(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category.getDeletedAt() == null) {
            return category;
        }
        return null;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category edit(UUID id, Category category) {
        Category dao = categoryRepository.findById(id).orElse(null);
        if (dao != null) {
            if (dao.getDeletedAt() == null) {
                dao.setName(category.getName());
//                dao.setUpdatedAt(new Date());
                return categoryRepository.save(dao);
            }
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null){
            category.setDeletedAt(new Date());
            categoryRepository.save(category);
            return true;
        }
        return false;
    }
}
