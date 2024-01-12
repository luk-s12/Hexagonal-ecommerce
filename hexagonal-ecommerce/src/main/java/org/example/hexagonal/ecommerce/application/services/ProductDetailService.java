package org.example.hexagonal.ecommerce.application.services;

import java.util.ArrayList;
import java.util.List;

import org.example.hexagonal.ecommerce.application.repositories.OfferRepository;
import org.example.hexagonal.ecommerce.application.repositories.ProductRepository;
import org.example.hexagonal.ecommerce.application.repositories.SizeRepository;
import org.example.hexagonal.ecommerce.domain.Offer;
import org.example.hexagonal.ecommerce.domain.Product;
import org.example.hexagonal.ecommerce.domain.ProductDetail;
import org.example.hexagonal.ecommerce.domain.Size;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProductDetailService {

    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;
    private final SizeRepository sizeRepository;

    public ProductDetailService( ProductRepository productRepository, SizeRepository sizeRepository,
    							 OfferRepository offerRepository) 
    {
        this.productRepository = productRepository;
        this.sizeRepository    = sizeRepository;
        this.offerRepository   = offerRepository;
    }

    @Transactional(readOnly = true)
    public ProductDetail getProductDetail(Integer id) {

        Product product = this.productRepository.findById(id);
        Offer offer 	= this.offerRepository.findByIdAndValidFromCustom(id);
        Size size 		= this.sizeRepository.getSizeByProducId(id);

        return ProductDetail.builder()
                .id(String.valueOf(product.getId()))
                .name(product.getName())
                .price(offer.getPrice())
                .availability(size.getAvailability())
                .build();

    }
    
    public List<ProductDetail> getProductDetailsForIds(List<Integer> ids) {
        List<ProductDetail> response = new ArrayList<>();
        ids.forEach(id -> response.add(this.getProductDetail(id)) );
        return response;
    }

}
