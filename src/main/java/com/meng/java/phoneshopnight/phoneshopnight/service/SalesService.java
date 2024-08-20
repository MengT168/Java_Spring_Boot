package com.meng.java.phoneshopnight.phoneshopnight.service;

import com.meng.java.phoneshopnight.phoneshopnight.dto.SaleDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Sale;

public interface SalesService {
	void sell(SaleDTO saleDTO);
	Sale getById(Long cancelId);
	void cancelSale(Long saleId);
}
