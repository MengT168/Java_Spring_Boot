package com.meng.java.phoneshopnight.phoneshopnight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long> , JpaSpecificationExecutor<ProductImportHistory> {
	
}
