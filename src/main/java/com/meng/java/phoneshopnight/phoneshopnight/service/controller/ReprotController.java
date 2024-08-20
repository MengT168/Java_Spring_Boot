package com.meng.java.phoneshopnight.phoneshopnight.service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductExpenseDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductReportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.service.ReportService;
import com.meng.java.phoneshopnight.phoneshopnight.service.projection.ProductSold;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
public class ReprotController {
	
	private final ReportService reportService;
	
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> report(
	         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
	         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
	    
		List<ProductSold> productsSold = reportService.getProductSold(startDate, endDate);
		return ResponseEntity.ok(productsSold);
	}
	
	@PreAuthorize("hasAuthority('report:read')")
	@GetMapping("v2/{startDate}/{endDate}")
	public ResponseEntity<?> reportv2(
	         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
	         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
	    
		List<ProductReportDTO> productsSold = reportService.getProductReport(startDate, endDate);
		return ResponseEntity.ok(productsSold);
	}
	
	@PreAuthorize("hasAuthority('report:read')")
	@GetMapping("expense/{startDate}/{endDate}")
	public ResponseEntity<?> expenseReport(
	         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate") LocalDate startDate,
	         @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate") LocalDate endDate) {
	    
		List<ProductExpenseDTO> productExpenseReport = reportService.getProductExpenseReport(startDate, endDate);
		return ResponseEntity.ok(productExpenseReport);
	}
}
