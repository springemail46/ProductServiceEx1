package com.spring.service;

import com.spring.entity.Product;
import com.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public Product getProduct(long id){
        return repository.findById(id).get();
    }
    public List<Product> getProductList(){
        return repository.findAll();
    }
    public Product updateProduct(Product product, long id){
        return repository.findById(id).map(p ->{
            p.setName(product.getName());
            p.setQty(product.getQty());
            p.setPrice(product.getPrice());
            p.setType(product.getType());
            return repository.save(p);
        }).orElseGet(()->{
            product.setId(id);
            return repository.save(product);
        });
    }
    public Map<String, Boolean> deleteProduct(long id){
        repository.findById(id).orElseThrow(()-> new RejectedExecutionException("Product is not able to delete"+id));
        repository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Product Deleted ", Boolean.TRUE);
        return response;
    }
}
