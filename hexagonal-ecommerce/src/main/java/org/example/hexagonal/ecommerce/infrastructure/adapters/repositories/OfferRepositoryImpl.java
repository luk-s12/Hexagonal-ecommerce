package org.example.hexagonal.ecommerce.infrastructure.adapters.repositories;

import java.sql.Timestamp;

import java.util.Optional;

import org.example.hexagonal.ecommerce.application.repositories.OfferRepository;
import org.example.hexagonal.ecommerce.application.services.DataTimeService;
import org.example.hexagonal.ecommerce.domain.Offer;
import org.example.hexagonal.ecommerce.infrastructure.entities.OfferEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class OfferRepositoryImpl implements OfferRepository {

    @Value("${date}")
    private String presentDay;

    private final OfferJpaRepository offerJpaRepository;

    public OfferRepositoryImpl(OfferJpaRepository offerJpaRepository) {
        this.offerJpaRepository = offerJpaRepository;
    }

    @Override
    public Offer findByIdAndValidFromCustom(Integer id) {
        Timestamp timestamp = DataTimeService.coverToTimestamp(presentDay);
        
        Optional<OfferEntity> offerEntityOptional = this.offerJpaRepository.findByIdAndValidFromCustom(id, timestamp);
        
        if (offerEntityOptional.isEmpty())
           throw new RuntimeException("Offer is empty");

        OfferEntity offerEntity = offerEntityOptional.get();

        return Offer.builder()
                .id(offerEntity.getId())
                .validFrom(offerEntity.getValidFrom())
                .price(offerEntity.getPrice())
                .build();
    }

}
