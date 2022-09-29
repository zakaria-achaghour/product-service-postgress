package com.product.service.services.impl;

import com.product.service.model.Product;
import com.product.service.repostitories.ProductRepository;
import com.product.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(c -> {
            if (c.getDeletedAt() == null) {
                products.add(c);
            }
        });
        return products;
    }

    @Override
    public Product get(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product.getDeletedAt() == null) {
            return product;
        }
        return null;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product edit(UUID id, Product product) {
        Product dao = productRepository.findById(id).orElse(null);
        if (dao != null) {
            if (dao.getDeletedAt() == null) {
                return productRepository.save(product);
            }
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null){
            product.setDeletedAt(new Date());
            productRepository.save(product);
            return true;
        }
        return false;
    }
}
