package com.spring.controller;

import com.spring.entity.Product;
import com.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;
    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable long id){
        return service.getProduct(id);
    }
    @GetMapping("/getAllProduct")
    public List<Product> getProducts(){
        return service.getProductList();
    }
    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable long id){
        return service.updateProduct(product, id);
    }
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable long id){
        return service.deleteProduct(id);
    }
}
