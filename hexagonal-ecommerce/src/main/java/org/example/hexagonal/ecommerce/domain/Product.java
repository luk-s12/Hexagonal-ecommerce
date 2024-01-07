package org.example.hexagonal.ecommerce.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product{
	
	private Integer id;
    private String name;

}
