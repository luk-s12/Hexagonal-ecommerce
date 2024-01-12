package org.example.hexagonal.ecommerce.infrastructure.rest;

import java.util.List;

import org.example.hexagonal.ecommerce.application.services.MockServerClientService;
import org.example.hexagonal.ecommerce.application.services.ProductDetailService;
import org.example.hexagonal.ecommerce.domain.ProductDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class SimilarController {

	private final MockServerClientService mockServerClient;
	private final ProductDetailService productDetailService;

	public SimilarController(MockServerClientService mockServerClient, ProductDetailService productDetailService) {
		this.mockServerClient = mockServerClient;
		this.productDetailService = productDetailService;
	}

	@GetMapping("{productId}/similar")
	public ResponseEntity< ? > similarProducts(@PathVariable(required = true) String productId) {
		List<Integer> ids = this.mockServerClient.getSimilarProductIds(productId);
		
		if( ids.isEmpty() )
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products similar were found");
			
		List<ProductDetail> response = this.productDetailService.getProductDetailsForIds(ids);

		return ResponseEntity.ok(response);
	}

}
