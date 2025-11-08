package com.Digital.Commerce.System.Shopping.service;

import com.Digital.Commerce.System.Shopping.entity.Type;
import com.Digital.Commerce.System.Shopping.model.BrandResponse;
import com.Digital.Commerce.System.Shopping.model.TypeResponse;
import com.Digital.Commerce.System.Shopping.repository.BrandRepository;
import com.Digital.Commerce.System.Shopping.repository.TypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TypeServiceImpl implements TypeService
{
    private final TypeRepository typeRepository;


    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }


    @Override
    public List<TypeResponse> getAllTypes() {

        log.info("Fetching All Types");

        //FETCH TYPE FROM DB
        List<Type> typeList =typeRepository.findAll();


        //Now ue Stream Operator to Map with response
        List<TypeResponse> typeResponse = typeList.stream()
                .map(this::convertToTypeResponse)
                .collect(Collectors.toList());

        return typeResponse;

    }

    private TypeResponse convertToTypeResponse(Type type) {
        return TypeResponse.builder()
                .id(type.getId())
                .name((type.getName()))
                .build();
    }

}
