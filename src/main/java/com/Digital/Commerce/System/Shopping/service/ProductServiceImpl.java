package com.Digital.Commerce.System.Shopping.service;


import com.Digital.Commerce.System.Shopping.entity.Product;
import com.Digital.Commerce.System.Shopping.model.ProductResponse;
import com.Digital.Commerce.System.Shopping.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponse getProductById(Integer productId) {
        log.info("Fetching Product By Id :{}", productId);
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Doesnt exist"));

        //Now conver the product to ProductResponse
        ProductResponse productResponse = convertToProductResponse(product);
        log.info("Fetched Product by Id:{}", productId);
        return productResponse;
    }

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable, Integer brandId, Integer typeId, String keyword) {
        //    public List<ProductResponse> getProducts() { //without page

        log.info("Fetching Products!!!");

        Specification<Product> spec = Specification.where(null);

        if (brandId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("brand").get("id"), brandId));
        }

        if (typeId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("type").get("id"), typeId));
        }

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));
        }

        log.info("Fetched All Products!!!");
        return productRepository.findAll(spec, pageable)
                .map(this::convertToProductResponse);



//        //Fetching from DB
////        List<Product> productList = productRepository.findAll();  //without page
//          Page<Product> productPage = productRepository.findAll(pageable);  //with page
//
////        List<ProductResponse> productResponses = productList.stream()  //without page
////                .map(this::convertToProductResponse)
////                .collect(Collectors.toList());
//
//          Page<ProductResponse> productResponses = productPage   //with page
//                .map(this::convertToProductResponse);
//
//        return productResponses;
    }



    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .PictureUrl(product.getPictureUrl())
                .productbrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .build();
    }

}
