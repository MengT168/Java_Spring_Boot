package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductImportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ApiException;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.mapper.ProductMapper;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductImportHistoryRepository;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ProductRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.ProductService;

import liquibase.repackaged.org.apache.commons.collections4.map.HashedMap;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository importHistoryRepository;
	private final ProductMapper productMapper ;
	
	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(), product.getColor().getName()) ;
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		if(importDTO.getImportUnit() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Value can't be null");
		}
		//Update available product unit
		Product product = getById(importDTO.getProductId());
		Integer updateUnit = 0;
//		Integer updateUnit = product.getAvailableUnit() + importDTO.getImportUnit();
		if(product.getAvailableUnit() != null) {
			updateUnit = product.getAvailableUnit();
		}
		product.setAvailableUnit(updateUnit + importDTO.getImportUnit());
		productRepository.save(product);
		//save product import history
		ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
		importHistoryRepository.save(importHistory);
	}

	@Override
	public void setSellPrice(Long id, BigDecimal price) {
		Product product = getById(id);
		product.setSalePrice(price);
		productRepository.save(product);
	}
	
	@Override
	public void ValidateStock(Long ProductId, Integer numberOfUnits) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, String> uploadProduct(MultipartFile file) {
		Map<Integer, String> map = new HashedMap<>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("products");
			Iterator<Row> rowIterator = sheet.iterator();
			
			rowIterator.next();
			
			while(rowIterator.hasNext()) {
				Integer rowNumber = 0;
				try {
					Row row = rowIterator.next();
					int cellIndex = 0;
					
					Cell cellNo = row.getCell(cellIndex++);
					rowNumber = (int) cellNo.getNumericCellValue();
					
					Cell cellModelId = row.getCell(cellIndex++);
					Long modelId =  (long) cellModelId.getNumericCellValue();
					
					Cell cellColorId = row.getCell(cellIndex++); 
					Long colorId =  (long) cellColorId.getNumericCellValue();
					
					Cell cellImportPrice = row.getCell(cellIndex++);
					Double importPrice =  cellImportPrice.getNumericCellValue();
					if(importPrice < 1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
					}
					
					Cell cellImportUnit = row.getCell(cellIndex++);
					Integer importUnit =  (int) cellImportUnit.getNumericCellValue();
					if(importUnit < 1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0");
					}
					
					Cell cellImportDate = row.getCell(cellIndex++);
					LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();
					
					Product product = getByModelIdAndColorId(modelId, colorId);
					
					
					//System.out.println(modelId);
					Integer availableUnit = 0;
					if(product.getAvailableUnit() != null) {
						availableUnit = product.getAvailableUnit();
					}
					product.setAvailableUnit(availableUnit + importUnit);
					productRepository.save(product);
					
					// save product import history
					ProductImportHistory importHistory = new ProductImportHistory();
					importHistory.setDateImport(importDate);
					importHistory.setImportUnit(importUnit);
					importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
					importHistory.setProduct(product);
					importHistoryRepository.save(importHistory);
				}catch(Exception e) {
					map.put(rowNumber, e.getMessage());
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		
		
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST,"Product with model id =%s and color id = %d was not found".formatted(modelId, colorId)));
	}

	


}
