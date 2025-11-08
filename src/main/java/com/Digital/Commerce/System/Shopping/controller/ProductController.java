package com.Digital.Commerce.System.Shopping.controller;

import com.Digital.Commerce.System.Shopping.entity.Product;
import com.Digital.Commerce.System.Shopping.model.BrandResponse;
import com.Digital.Commerce.System.Shopping.model.ProductResponse;
import com.Digital.Commerce.System.Shopping.model.TypeResponse;
import com.Digital.Commerce.System.Shopping.service.BrandService;
import com.Digital.Commerce.System.Shopping.service.ProductService;
import com.Digital.Commerce.System.Shopping.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final TypeService typeService;
    private final BrandService brandService;

    public ProductController(ProductService productService, TypeService typeService, BrandService brandService) {
        this.productService = productService;
        this.typeService = typeService;
        this.brandService = brandService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id) {
        ProductResponse productResponse = productService.getProductById(id);
        return  new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


//    @GetMapping()
//    public ResponseEntity<List<ProductResponse>>getProducts(){  //Without Page
//
//            List<ProductResponse> productResponses = productService.getProducts();
//            return new ResponseEntity<>(productResponses, HttpStatus.OK);
//    }


    @GetMapping()      // With Page
    public ResponseEntity<Page<ProductResponse>>getProducts(
            @RequestParam(name = "page", defaultValue = "0")int page,
            @RequestParam(name = "size", defaultValue = "10")int size,

            @RequestParam(name = "keyword",required = false)String keyword,
            @RequestParam(name = "brandId",required = false)Integer brandId,
            @RequestParam(name = "typeId",required = false)Integer typeId,

            @RequestParam(name = "sort",defaultValue = "name")String sort,
            @RequestParam(name = "order",defaultValue = "asc")String order
    )
    {
        //Convert order to Sort direction
        Sort.Direction direction =  order.equalsIgnoreCase ("desc") ? Sort.Direction.DESC: Sort.Direction.ASC;
        Sort sorting = Sort.by(direction, sort);
        Pageable pageable =  PageRequest.of(page, size, sorting);

        Page<ProductResponse> productResponses = productService.getProducts(pageable, brandId,typeId, keyword);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getBrands()
    {
        List<BrandResponse> brandResponses = brandService.getAllBrand();
        return new ResponseEntity<>(brandResponses, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<TypeResponse>> getTypes()
    {
        List<TypeResponse> typeResponses = typeService.getAllTypes();
        return new ResponseEntity<>(typeResponses, HttpStatus.OK);
    }


}
