package com.meng.java.phoneshopnight.phoneshopnight.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Sale;
import com.meng.java.phoneshopnight.phoneshopnight.service.projection.ProductSold;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query(value = "SELECT p.product_id as productId , p.product_name as productName  , sum(sd.unit) unit , sum(sd.sold_amount * sd.unit) totalAmount\r\n"
			+ "FROM sale_details sd\r\n"
			+ "INNER JOIN sales s ON sd.sale_id = s.sale_id\r\n"
			+ "INNER JOIN products p ON p.product_id = sd.product_id\r\n"
			+ "WHERE DATE(s.sold_date) >= :startDate AND DATE(s.sold_date) <= :endDate\r\n"
			+ "AND s.status = true\r\n"
			+ "GROUP BY p.product_id , p.product_name\r\n"
			+ "" , nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate startDate , LocalDate endDate);
	
}
