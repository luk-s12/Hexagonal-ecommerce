package org.example.hexagonal.ecommerce.infrastructure.adapters.repositories;

import java.sql.Timestamp;
import java.util.Optional;

import org.example.hexagonal.ecommerce.infrastructure.entities.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfferJpaRepository extends JpaRepository<OfferEntity, Integer> {

    @Query("SELECT o FROM OfferEntity o WHERE o.product.id = :id AND o.validFrom <= :validFrom ORDER BY o.validFrom DESC LIMIT 1")
    public Optional<OfferEntity> findByIdAndValidFromCustom(@Param("id") Integer id, @Param("validFrom") Timestamp validFrom);

}
