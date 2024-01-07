package org.example.hexagonal.ecommerce.application.repositories;

import org.example.hexagonal.ecommerce.domain.Offer;

public interface OfferRepository {

	Offer findByIdAndValidFromCustom(Integer id);

}
