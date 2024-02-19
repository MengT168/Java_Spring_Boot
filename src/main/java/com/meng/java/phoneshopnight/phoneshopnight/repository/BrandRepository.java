package com.meng.java.phoneshopnight.phoneshopnight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	
}
