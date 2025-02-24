package com.ms.master.product.imp;
import com.ms.master.product.dto.ProductRequest;
import com.ms.master.product.dto.ProductResponse;
import com.ms.master.product.model.Product;
import com.ms.master.product.repository.ProductRepository;
import com.ms.master.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private  ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product=Product.builder().name(productRequest.name())
                .description(productRequest.description())
                .price(String.valueOf(productRequest.price())).build();
        productRepository.save(product);
        log.info("Product is created SuccessFully");
    return new ProductResponse(product.getId(),
            product.getName(),product.getDescription(),product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream().map(product -> new ProductResponse(product.getId(),
                        product.getName(),product.getDescription(),product.getPrice())).toList();
    }

}
