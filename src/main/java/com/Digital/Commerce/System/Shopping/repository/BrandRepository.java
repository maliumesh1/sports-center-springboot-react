package com.Digital.Commerce.System.Shopping.repository;

import com.Digital.Commerce.System.Shopping.entity.Brand;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>
{

}
