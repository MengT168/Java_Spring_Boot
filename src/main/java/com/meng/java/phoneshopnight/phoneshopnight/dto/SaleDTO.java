package com.meng.java.phoneshopnight.phoneshopnight.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaleDTO {
	@NotEmpty
	private List<ProductSoldDTO> products;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime SaleDate;
}
