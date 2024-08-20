package com.meng.java.phoneshopnight.phoneshopnight.service;

import java.time.LocalDate;
import java.util.List;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductExpenseDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductReportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.service.projection.ProductSold;

public interface ReportService {
	List<ProductSold> getProductSold(LocalDate startDate , LocalDate endDate);
	List<ProductReportDTO> getProductReport(LocalDate startDate , LocalDate endDate);
	List<ProductExpenseDTO> getProductExpenseReport(LocalDate startDate , LocalDate endDate);
}
