package com.ms.master.product.controller;
import com.ms.master.product.dto.ProductRequest;
import com.ms.master.product.dto.ProductResponse;
import com.ms.master.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
   // @Autowired
   // private final ProductServiceImpl productServiceimpl;
    @Autowired
    private final ProductService productServiceimpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest)
    {
        System.out.println("inside the create methods-->" + productRequest);
      return productServiceimpl.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {/*
        try{Thread.sleep(5000);}
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }*/
        return productServiceimpl.getAllProducts();
    }
}
