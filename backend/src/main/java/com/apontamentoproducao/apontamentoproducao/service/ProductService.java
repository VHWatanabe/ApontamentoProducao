package com.apontamentoproducao.apontamentoproducao.service;

import com.apontamentoproducao.apontamentoproducao.domain.Product;
import com.apontamentoproducao.apontamentoproducao.dto.ProductRequest;
import com.apontamentoproducao.apontamentoproducao.exception.BusinessException;
import com.apontamentoproducao.apontamentoproducao.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductRequest request) {

        productRepository.findByCode(request.getCode())
                .ifPresent(product -> {
                    throw new BusinessException("Já existe produto com este código");
                });

        Product product = new Product();

        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setUnitsPerBox(request.getUnitsPerBox());

        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findByActiveTrue();
    }
}