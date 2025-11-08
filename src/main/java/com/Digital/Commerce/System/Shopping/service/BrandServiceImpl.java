package com.Digital.Commerce.System.Shopping.service;

import com.Digital.Commerce.System.Shopping.entity.Brand;
import com.Digital.Commerce.System.Shopping.model.BrandResponse;
import com.Digital.Commerce.System.Shopping.repository.BrandRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponse> getAllBrand() {
        log.info("Fetching All Brands");

        // Fetch all brands
        List<Brand> brandList = brandRepository.findAll();

        // Convert Brand entities to BrandResponse DTOs
        List<BrandResponse> brandResponse = brandList.stream()
                .map(this::convertToBrandResponse)
                .collect(Collectors.toList());

        log.info("Fetched " + brandResponse.size() + " brands successfully.");
        return brandResponse;
    }

    private BrandResponse convertToBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
