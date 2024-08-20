package com.meng.java.phoneshopnight.phoneshopnight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> , JpaSpecificationExecutor<Brand> {
//	List<Brand> findByName(String name);
//	List<Brand> findByNameIgnoreCase(String name);
	List<Brand> findByNameContaining(String name);
}
