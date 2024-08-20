package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductSoldDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.SaleDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Sale;
import com.meng.java.phoneshopnight.phoneshopnight.entity.SaleDetail;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ApiException;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.SaleDetailRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.SaleRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.ProductService;
import com.meng.java.phoneshopnight.phoneshopnight.service.SalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SalesService {
	
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;
	@Override
	public void sell(SaleDTO saleDTO) {
		//================Validate==================//
		//validate products
				List<Long> ProductIds = saleDTO.getProducts()
					.stream()
					.map(ProductSoldDTO::getProductId)
					.toList();
				
				ProductIds.forEach(productService::getById);
				List<Product> products = productRepository.findAllById(ProductIds);
				Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
				//validate stock
				saleDTO.getProducts()
				.forEach(Ps -> {
					Product product = productMap.get(Ps.getProductId());
					if(product.getAvailableUnit()< Ps.getNumberOfUnit()) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is Invalid Stock".formatted(product.getName()));
					}
				});
				//================Save==================//
				//sale
				Sale sale = new Sale();
				sale.setSoldDate(saleDTO.getSaleDate());
				sale.setStatus(true);
				saleRepository.save(sale);
				
				//sale Detail
				saleDTO.getProducts().forEach(ps ->{
					Product product = productMap.get(ps.getProductId());
					SaleDetail saleDetail = new SaleDetail();
					saleDetail.setAmount(product.getSalePrice());
					saleDetail.setProduct(product);
					saleDetail.setSale(sale);
					saleDetail.setUnit(ps.getNumberOfUnit());
					saleDetailRepository.save(saleDetail);
				// Update Stock
					Integer availableUnit =  product.getAvailableUnit() - ps.getNumberOfUnit();
					product.setAvailableUnit(availableUnit);
					productRepository.save(product);
				});
	}
	@Override
	public void cancelSale(Long saleId) {
		//Update SaleStatus
		Sale sale = getById(saleId);
		sale.setStatus(false);
		saleRepository.save(sale);
		
		//Update Stock
		List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
		
		List<Long> productIds = saleDetails.stream()
		.map(sd->sd.getProduct().getId())
		.toList();
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		saleDetails.forEach(sd -> {
			Product product = productMap.get(sd.getProduct().getId());
			product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
			productRepository.save(product);
		});
		
	}
	
	
	
	@Override
	public Sale getById(Long cancelId) {
		
		return saleRepository.findById(cancelId)
				.orElseThrow(()-> new ResourceNotFoundException("sale", cancelId));
	}
	
	
	
	

}
