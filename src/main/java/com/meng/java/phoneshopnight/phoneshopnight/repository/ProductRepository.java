package com.meng.java.phoneshopnight.phoneshopnight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {
	
//	Optional<Product> finByModelIdAndColorId(Long modelId , Long colorId);
	Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
