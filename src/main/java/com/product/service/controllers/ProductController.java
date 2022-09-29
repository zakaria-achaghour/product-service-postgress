package com.product.service.controllers;

import com.product.service.model.Product;
import com.product.service.model.Product;
import com.product.service.request.ProductRequest;
import com.product.service.request.ProductRequest;
import com.product.service.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        Product product = productService.get(id);
        if (product == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        return new ResponseEntity<>(productService.add(product), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@Valid @RequestBody ProductRequest request, @PathVariable UUID id) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        Product product1 = productService.edit(id, product);
        if (product1 == null) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product1, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        boolean check =  productService.delete(id);
        if(check){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
