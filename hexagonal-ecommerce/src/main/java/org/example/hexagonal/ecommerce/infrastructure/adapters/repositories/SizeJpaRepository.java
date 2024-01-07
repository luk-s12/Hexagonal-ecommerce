package org.example.hexagonal.ecommerce.infrastructure.adapters.repositories;

import java.util.Optional;

import org.example.hexagonal.ecommerce.infrastructure.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SizeJpaRepository extends JpaRepository<SizeEntity, Integer> {

    @Query("SELECT s FROM SizeEntity s WHERE s.product.id = :id")
    Optional<SizeEntity> findByProducId(@Param("id") Integer id);

}
