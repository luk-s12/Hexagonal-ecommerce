package org.example.hexagonal.ecommerce.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Offer {
    
	private Integer id;
    private Timestamp validFrom;
    private BigDecimal price;
    private Product product;

}