package com.ms.master.product.service;

import com.ms.master.product.dto.ProductRequest;
import com.ms.master.product.dto.ProductResponse;
import com.ms.master.product.model.Product;
import com.ms.master.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface ProductService {
public ProductResponse createProduct(ProductRequest productRequest);
public List<ProductResponse> getAllProducts();
}
