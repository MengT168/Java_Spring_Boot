package com.meng.java.phoneshopnight.phoneshopnight.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProductImportDTO {
	
	@NotNull(message = "Product id can't be Null")
	private Long productId;
	
	@Min(value = 1 , message = "Import must be greater than 0" )
	private Integer importUnit;
	
	@DecimalMin(value = "0.000001", message = "Price must be greater than 0")
	private BigDecimal importPrice;
	
	@NotNull(message = "Import date Can't be Null")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime importDate;
}
