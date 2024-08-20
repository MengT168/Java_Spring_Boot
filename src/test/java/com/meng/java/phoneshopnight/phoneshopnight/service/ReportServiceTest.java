package com.meng.java.phoneshopnight.phoneshopnight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductExpenseDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductImportHistoryRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.SaleDetailRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.SaleRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.impl.ReportServiceImpl;
import com.meng.java.phoneshopnight.phoneshopnight.spec.ProductImportHistorySpec;
import com.meng.java.phoneshopnight.phoneshopnight.util.ReportTestHelper;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	private SaleDetailRepository saleDetailRepository;
	@Mock
	private SaleRepository saleRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductImportHistoryRepository productImportHistoryRepository;
	
	private ReportService reportService;

	
	@BeforeEach
	public void setUp() {
		reportService = new ReportServiceImpl(saleDetailRepository, saleRepository, productRepository, productImportHistoryRepository);
	}
	
	@Test
	public void testGetProductExpenseReport() {
		///given
		List<ProductImportHistory> importHistories = ReportTestHelper.getProductImportHistories();
		List<Product> products = ReportTestHelper.getProducts();
		//when
		when(productImportHistoryRepository.findAll(Mockito.any(ProductImportHistorySpec.class)))
			.thenReturn(importHistories);
		
		when(productRepository.findAllById(anySet())).thenReturn(products);
		
		List<ProductExpenseDTO> expenseReports = reportService.getProductExpenseReport(LocalDate.now().minusMonths(1), LocalDate.now());
		//then
		
		assertEquals(2, expenseReports.size());
//		ProductExpenseDTO expense1 = expenseReports.get(0);
		ProductExpenseDTO expense1 = expenseReports.get(0);
		assertEquals(1, expense1.getProductId());
		assertEquals("14Pro", expense1.getProductName());
		assertEquals(24, expense1.getTotalUnit());
		assertEquals(BigDecimal.valueOf(29500.0), expense1.getTotalAmount());
	}
}
