
package org.example.hexagonal.ecommerce.infrastructure.adapters.repositories;

import java.util.Optional;

import org.example.hexagonal.ecommerce.application.repositories.ProductRepository;
import org.example.hexagonal.ecommerce.domain.Product;
import org.example.hexagonal.ecommerce.infrastructure.entities.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRespositorityImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductRespositorityImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product findById(Integer id) {
        Optional<ProductEntity> productOptional = this.productJpaRepository.findById(id);

        if (productOptional.isEmpty())
            throw new RuntimeException("Product is empty");

        ProductEntity productEntity = productOptional.get();

        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .build();

    }

}