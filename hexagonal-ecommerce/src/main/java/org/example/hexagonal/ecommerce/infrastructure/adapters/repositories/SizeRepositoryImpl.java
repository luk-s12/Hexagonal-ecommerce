package org.example.hexagonal.ecommerce.infrastructure.adapters.repositories;

import java.util.Optional;

import org.example.hexagonal.ecommerce.application.repositories.SizeRepository;
import org.example.hexagonal.ecommerce.domain.Product;
import org.example.hexagonal.ecommerce.domain.Size;
import org.example.hexagonal.ecommerce.infrastructure.entities.ProductEntity;
import org.example.hexagonal.ecommerce.infrastructure.entities.SizeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SizeRepositoryImpl implements SizeRepository {

    private final SizeJpaRepository sizeJpaRepository;

    public SizeRepositoryImpl(SizeJpaRepository sizeJpaRepository) {
        this.sizeJpaRepository = sizeJpaRepository;
    }

    @Override
    public Size getSizeByProducId(Integer id) {
        Optional<SizeEntity> sizeEntityOptional = this.sizeJpaRepository.findByProducId(id);

        if (sizeEntityOptional.isEmpty())
            throw new RuntimeException("Size is empty");
        
        SizeEntity sizeEntity = sizeEntityOptional.get();

        return Size.builder()
                   .id(sizeEntity.getId())
                   .size(sizeEntity.getSize())
                   .lastUpdate(sizeEntity.getLastUpdate())
                   .availability(sizeEntity.getAvailability())
                   .build();
    }

	@Override
	public Size update(Size sizeRequest) {
		
		if(sizeRequest == null || sizeRequest.getId() == null)
			return null;

		SizeEntity sizeEntity = this.convertoToEntity(sizeRequest); 
				
		sizeEntity = this.sizeJpaRepository.save( sizeEntity );
		
		Product product = getProduct(sizeEntity);
								 
		return Size.builder()
				   .id(sizeEntity.getId())
				   .availability(sizeEntity.getAvailability())
				   .lastUpdate(sizeEntity.getLastUpdate())
				   .product( product )
				   .build() ;
	}

	@Override
	public Size findById(Integer id) {
		Optional<SizeEntity> sizeEntityOptional = null;
		
		try {
			sizeEntityOptional = this.sizeJpaRepository.findById(id);			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		SizeEntity sizeEntity = null;
		
		if( !sizeEntityOptional.isPresent() )
			return null;
			
		sizeEntity = sizeEntityOptional.get();
		
		return this.convertToSize(sizeEntity);

	}

	private Product getProduct(SizeEntity sizeEntity) {
		ProductEntity productEntity = sizeEntity.getProduct();
		Product product = Product.builder().id(productEntity.getId()) 
										   .name(productEntity.getName())
										   .build();
		return product;
	}

	private Size convertToSize(SizeEntity sizeEntity){
		Product product = Product.builder()
								 .id(sizeEntity.getProduct().getId())
								 .name(sizeEntity.getProduct().getName())
								 .build();

		return Size.builder()
				   .id(sizeEntity.getId())
				   .availability(sizeEntity.getAvailability())
				   .lastUpdate(sizeEntity.getLastUpdate())
				   .size(sizeEntity.getSize())
				   .product( product )
				   .build();
	}
	
	private SizeEntity convertoToEntity(Size size) {
		ProductEntity productEntity = ProductEntity.builder()
												   .id(size.getProduct().getId())
												   .name(size.getProduct().getName())
												   .build();
		
		return SizeEntity.builder()
						 .id(size.getId())
						 .availability(size.getAvailability())
						 .lastUpdate(size.getLastUpdate())
						 .product( productEntity )
						 .size(size.getSize())
						 .build();
	}
}
