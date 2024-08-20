package com.meng.java.phoneshopnight.phoneshopnight.service.projection;

import java.math.BigDecimal;

public interface ProductSold {
	
	Long getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmount();
}
