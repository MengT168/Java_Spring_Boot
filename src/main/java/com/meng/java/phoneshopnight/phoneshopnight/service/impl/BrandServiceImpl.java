package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.BrandRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.BrandService;
import com.meng.java.phoneshopnight.phoneshopnight.spec.BrandFilter;
import com.meng.java.phoneshopnight.phoneshopnight.spec.BrandSpec;
import com.meng.java.phoneshopnight.phoneshopnight.util.PageUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private final BrandRepository brandRepository;
	
	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Long id) {
		/*Optional<Brand> brandOptional = brandRepository.findById(id);
		if(brandOptional.isEmpty()) {
			return brandOptional.get();
		}
//		throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id = "+id+" not found");
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id = %d not found".formatted(id));
	}*/
		
		return brandRepository.findById(id)
				/*
				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Brand with id = %d not found".formatted(id)));
				.orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND,"Brand with id = %d not found".formatted(id)));
				*/
				.orElseThrow(()->new ResourceNotFoundException("brand", id));
				
}

	@Override
	public Brand update(Long id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}


//	@Override
//	public List<Brand> getBrands() {
//		return brandRepository.findAll();
//	}

	@Override
	public List<Brand> getBrands(String name) {
		return brandRepository.findByNameContaining(name);
	}

	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
	
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		int pageLimit =PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		
		int pageNumber =PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		PageRequest pageable = PageUtil.getPageable(pageNumber, pageLimit);
		 Page<Brand> page = brandRepository.findAll(brandSpec , pageable);
		 return page;
	}

	@Override
	public void DeleteById(Long id) {
		Brand brand = getById(id);
		brandRepository.delete(brand);
		log.info("brand With id  = %d deleted".formatted(id));
	}


	
	
	
	}


