package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductExpenseDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductReportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;
import com.meng.java.phoneshopnight.phoneshopnight.entity.SaleDetail;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductImportHistoryRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.SaleDetailRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.SaleRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.ReportService;
import com.meng.java.phoneshopnight.phoneshopnight.service.projection.ProductSold;
import com.meng.java.phoneshopnight.phoneshopnight.spec.ProductImportHistoryFilter;
import com.meng.java.phoneshopnight.phoneshopnight.spec.ProductImportHistorySpec;
import com.meng.java.phoneshopnight.phoneshopnight.spec.SaleDetailFilter;
import com.meng.java.phoneshopnight.phoneshopnight.spec.SaleDetailSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final SaleDetailRepository saleDetailRepository;
	private final SaleRepository saleRepository;
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository productImportHistoryRepository;
	
	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRepository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> list = new ArrayList<>();
		SaleDetailFilter detailFilter = new SaleDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
		List<SaleDetail> saleDetail = saleDetailRepository.findAll(spec);
		
		List<Long> productId = saleDetail.stream()
			.map(sd -> sd.getProduct().getId())
			.toList();
		Map<Long, Product> productMap = productRepository
				.findAllById(productId).stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));
	
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetail.stream()
		.collect(Collectors.groupingBy(SaleDetail::getProduct));
		
		for(var entry : saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetail> sd = entry.getValue();
			Integer unit = sd.stream()
				.map(SaleDetail::getUnit)
				.reduce(0,(a,b)->a+b);
			
			//total amount
//			sd.stream()
//			.map(sd1->sd1.getUnit()* sd1.getAmount().doubleValue())
//			.reduce(0d,(a,b)->a+b);
			
			double totalAmount = sd.stream().mapToDouble(sd1->sd1.getUnit()* sd1.getAmount().doubleValue()).sum();
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			list.add(reportDTO);
		}
		
		return list;
	}

	@Override
	public List<ProductExpenseDTO> getProductExpenseReport(LocalDate startDate, LocalDate endDate) {
		ProductImportHistoryFilter productImportHistoryFilter = new ProductImportHistoryFilter();
		productImportHistoryFilter.setStartDate(startDate);
		productImportHistoryFilter.setEndDate(endDate);
		
		ProductImportHistorySpec spec = new ProductImportHistorySpec(productImportHistoryFilter);
		List<ProductImportHistory> importHistory = productImportHistoryRepository.findAll(spec);
		
		Set<Long> productIds = importHistory.stream().map(his -> his.getProduct().getId()).collect(Collectors.toSet());
		
		Map<Product, List<ProductImportHistory>> importMap = importHistory.stream().collect(Collectors.groupingBy(ip->ip.getProduct()));
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		var expenseDTOs = new ArrayList<ProductExpenseDTO>();
		
		for(var entry : importMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			
			List<ProductImportHistory> producthis = entry.getValue();
			int totalUnit = producthis.stream().mapToInt(ip->ip.getImportUnit()).sum();
			double totalAmount = producthis.stream().mapToDouble(ip->ip.getImportUnit()* ip.getPricePerUnit().doubleValue()).sum();
			
			
			var expenseDTO = new ProductExpenseDTO();
			expenseDTO.setProductId(product.getId());
			expenseDTO.setProductName(product.getName());
			expenseDTO.setTotalUnit(totalUnit);
			expenseDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			expenseDTOs.add(expenseDTO);
		}
		Collections.sort(expenseDTOs, (a,b)->(int)(a.getProductId()-b.getProductId()));
		return expenseDTOs;
	}

}
