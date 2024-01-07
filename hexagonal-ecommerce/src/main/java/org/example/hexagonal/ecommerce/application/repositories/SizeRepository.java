package org.example.hexagonal.ecommerce.application.repositories;

import org.example.hexagonal.ecommerce.domain.Size;

public interface SizeRepository {

    Size getSizeByProducId(Integer id);
    
    Size update(Size sizeEntity);
    
    Size findById(Integer id);

}
