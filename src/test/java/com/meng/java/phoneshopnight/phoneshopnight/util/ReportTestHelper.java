package com.meng.java.phoneshopnight.phoneshopnight.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;

public class ReportTestHelper {
	
	public static Product product1 = Product.builder()
			.id(1L)
			.name("14Pro")
			.build();
	
	public static Product product2 = Product.builder()
			.id(2L)
			.name("15Pro")
			.build();
	
	public static Product product3 = Product.builder()
			.id(3L)
			.name("13Pro")
			.build();
	
	public static List<Product> getProducts(){
		return List.of(product1,product2);
	}
	
	public static List<ProductImportHistory> getProductImportHistories(){
		ProductImportHistory history1 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(10)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.dateImport(LocalDateTime.of(2023, 1, 1, 8, 50))
				.build();
		
		ProductImportHistory history2 = ProductImportHistory.builder()
				.product(product2)
				.importUnit(15)
				.pricePerUnit(BigDecimal.valueOf(1300))
				.dateImport(LocalDateTime.of(2023, 1, 15, 11, 50))
				.build();
		
		ProductImportHistory history3 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(14)
				.pricePerUnit(BigDecimal.valueOf(1250))
				.dateImport(LocalDateTime.of(2023, 1, 1, 10, 50))
				.build();
		return List.of(history1,history2,history3);
	}
	
}
