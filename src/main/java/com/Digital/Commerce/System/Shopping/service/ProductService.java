package com.Digital.Commerce.System.Shopping.service;
import com.Digital.Commerce.System.Shopping.model.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService
{
    ProductResponse getProductById(Integer productId);

//    List<ProductResponse> getProducts(); //without page
    Page<ProductResponse>getProducts(Pageable pageable,Integer brandId, Integer typeId, String keyword);  //with page

}
