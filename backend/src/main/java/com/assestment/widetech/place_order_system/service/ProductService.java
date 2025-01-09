package com.assestment.widetech.place_order_system.service;

import com.assestment.widetech.place_order_system.dto.ProductDto;
import com.assestment.widetech.place_order_system.model.Product;
import com.assestment.widetech.place_order_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(),savedProduct.getDescription(), savedProduct.getStockQuantity());
    }

    public Optional<ProductDto> getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(),p.getDescription(), p.getStockQuantity()));
    }

    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getDescription(), p.getStockQuantity()));
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
