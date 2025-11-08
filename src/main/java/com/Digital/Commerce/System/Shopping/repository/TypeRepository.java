package com.Digital.Commerce.System.Shopping.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Digital.Commerce.System.Shopping.entity.Type;



@Repository
public interface TypeRepository extends JpaRepository<Type, Integer>
{


}
