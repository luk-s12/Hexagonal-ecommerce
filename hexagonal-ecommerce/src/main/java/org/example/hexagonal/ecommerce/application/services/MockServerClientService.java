package org.example.hexagonal.ecommerce.application.services;

import java.util.List;

public interface MockServerClientService {
	
	List<Integer> getSimilarProductIds(String productId);

}
