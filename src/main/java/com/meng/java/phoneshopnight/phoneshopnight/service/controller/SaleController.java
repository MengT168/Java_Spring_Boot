package com.meng.java.phoneshopnight.phoneshopnight.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.java.phoneshopnight.phoneshopnight.dto.SaleDTO;
import com.meng.java.phoneshopnight.phoneshopnight.service.SalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
public class SaleController {
	private final SalesService salesService;
	
	@PostMapping
	public ResponseEntity<?> SaleProduct(@RequestBody SaleDTO saleDTO){
		salesService.sell(saleDTO);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId ){
		salesService.cancelSale(saleId);
		return ResponseEntity.ok().build();
	}
}
