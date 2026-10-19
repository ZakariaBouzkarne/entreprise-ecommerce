package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @PostMapping("/create")
    public Product createProduct(@Valid @RequestBody Product product)
    {
        return productService.saveProduct(product);

    }

    @GetMapping("/All")
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Optional<Product>  product = productService.getProductById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        return  ResponseEntity.notFound().build();
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        boolean deleted = productService.deleteProduct(id);
        if(!deleted){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id ,@Valid @RequestBody Product newProduct){
             Optional<Product> updatedProduct= productService.updateProduct(id , newProduct);
             if(updatedProduct.isPresent())
             {
                 return ResponseEntity.ok(updatedProduct.get());
             }
             return ResponseEntity.notFound().build();

    }
}
