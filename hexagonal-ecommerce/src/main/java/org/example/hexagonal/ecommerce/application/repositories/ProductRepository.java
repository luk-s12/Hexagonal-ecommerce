package org.example.hexagonal.ecommerce.application.repositories;

import org.example.hexagonal.ecommerce.domain.Product;

public interface ProductRepository {

	Product findById(Integer id);

}
