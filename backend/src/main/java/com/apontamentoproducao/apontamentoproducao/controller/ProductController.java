package com.apontamentoproducao.apontamentoproducao.controller;

import com.apontamentoproducao.apontamentoproducao.domain.Product;
import com.apontamentoproducao.apontamentoproducao.dto.ProductRequest;
import com.apontamentoproducao.apontamentoproducao.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@RequestBody ProductRequest request) {
        return productService.create(request);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }
}