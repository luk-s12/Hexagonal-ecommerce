package org.example.hexagonal.ecommerce.infrastructure.events;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAvailabilityEvent implements Serializable {

	private static final long serialVersionUID = -6915528229848073283L;

	private Long sizeId;

	private boolean availability;

	private Timestamp update;

}
