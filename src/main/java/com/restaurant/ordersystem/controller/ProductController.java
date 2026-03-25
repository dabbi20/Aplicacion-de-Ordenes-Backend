package com.restaurant.ordersystem.controller;
import com.restaurant.ordersystem.entity.Product;
import com.restaurant.ordersystem.repository.ProductRepository;
import com.restaurant.ordersystem.service.ProductService;
import org.springframework.web.bind.annotation.*;

import  java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService){
this.productService = productService;
    }

    //CREAR PRODUCTO
    @PostMapping
    public  Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    //OBTENER PRODUCTOS

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    //OBTENER POR ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }
    //ACTUALIZAR
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    //BORRAR
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
