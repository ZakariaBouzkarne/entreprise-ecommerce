package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository ;



    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product>  getProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }

    public boolean deleteProduct(String id){
        if(!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    public Optional<Product> updateProduct(String id , Product newProduct)
    {
        Optional<Product> existingProduct = productRepository.findById(id);
        if(existingProduct.isPresent())
        {
             Product product = existingProduct.get();
             product.setName(newProduct.getName());
             product.setPrice(newProduct.getPrice());
             productRepository.save(product);
             return Optional.of(product);
        }
        return Optional.empty();


    }


}
