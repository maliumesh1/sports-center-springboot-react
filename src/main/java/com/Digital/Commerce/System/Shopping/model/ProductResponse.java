package com.Digital.Commerce.System.Shopping.model;

import com.Digital.Commerce.System.Shopping.entity.Brand;
import com.Digital.Commerce.System.Shopping.entity.Type;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Long price;
    private String PictureUrl;

    private String productbrand;
    private String productType;

}
