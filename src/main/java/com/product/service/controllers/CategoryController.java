package com.product.service.controllers;

import com.product.service.model.Category;
import com.product.service.request.CategoryRequest;
import com.product.service.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCcategory(@PathVariable UUID id) {
        Category category = categoryService.get(id);
        if (category == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<>(category, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category);
        return new ResponseEntity<>(categoryService.add(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@Valid @RequestBody CategoryRequest request, @PathVariable UUID id) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category);
        Category category1 = categoryService.edit(id, category);
        if (category1 == null) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable UUID id) {
        boolean check = categoryService.delete(id);
        if (check) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
