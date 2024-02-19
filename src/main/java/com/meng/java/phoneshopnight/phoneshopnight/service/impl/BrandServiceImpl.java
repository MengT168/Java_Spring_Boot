package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.BrandRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Integer id) {
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
				.orElseThrow(()-> new ResourceNotFoundException("brand", id));
		
}

	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}

	
}

