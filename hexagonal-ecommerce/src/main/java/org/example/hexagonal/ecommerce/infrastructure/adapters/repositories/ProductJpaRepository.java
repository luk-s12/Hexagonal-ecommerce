package org.example.hexagonal.ecommerce.infrastructure.adapters.repositories;

import org.example.hexagonal.ecommerce.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {
}
