package com.meng.java.phoneshopnight.phoneshopnight.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductExpenseDTO {
	private String productName;
	private Long productId;
	private Integer totalUnit;
	private BigDecimal totalAmount;
}
