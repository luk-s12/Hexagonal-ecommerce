package org.example.hexagonal.ecommerce.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetail{

	private String id;
	private String name;
	private BigDecimal price;
	private Boolean availability;
	
}
