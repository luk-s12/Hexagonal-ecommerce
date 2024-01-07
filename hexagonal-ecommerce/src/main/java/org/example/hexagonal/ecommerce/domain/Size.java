package org.example.hexagonal.ecommerce.domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Size{

	private Integer id;
    private String size;
    private Boolean availability;
    private Timestamp lastUpdate;
    private Product product;
    
}
