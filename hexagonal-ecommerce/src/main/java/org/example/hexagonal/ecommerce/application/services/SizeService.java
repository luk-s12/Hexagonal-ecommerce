package org.example.hexagonal.ecommerce.application.services;

import org.example.hexagonal.ecommerce.application.repositories.SizeRepository;
import org.example.hexagonal.ecommerce.domain.Size;
import org.example.hexagonal.ecommerce.infrastructure.events.ProductAvailabilityEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SizeService {

	private final SizeRepository sizeRepository;
	
	public SizeService(SizeRepository sizeRepository) {
		this.sizeRepository = sizeRepository;
	}
	
	@Transactional
	public Size updateSizeByProductAvailabilityEvent(ProductAvailabilityEvent productAvailabilityEvent) {
		
		if( productAvailabilityEvent == null  || productAvailabilityEvent.getSizeId() == null )
			return null;
		
		Integer id =  Integer.valueOf( productAvailabilityEvent.getSizeId().toString() );
		Size size  = this.sizeRepository.findById( id );
		
		if( size == null)
			return null; 
		
		size.setAvailability( productAvailabilityEvent.isAvailability() );
		size.setLastUpdate( productAvailabilityEvent.getUpdate() );
		
		return  this.sizeRepository.update(size);
	}
	
}
