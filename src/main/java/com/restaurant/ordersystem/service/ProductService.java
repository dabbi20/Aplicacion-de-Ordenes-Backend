package com.restaurant.ordersystem.service;

import com.restaurant.ordersystem.entity.Product;
import com.restaurant.ordersystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setAvailable(updatedProduct.getAvailable());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}