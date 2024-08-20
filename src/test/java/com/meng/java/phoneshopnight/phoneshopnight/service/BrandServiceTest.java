package com.meng.java.phoneshopnight.phoneshopnight.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.BrandRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	
	@Mock
	private BrandRepository brandRepository;
	
	private BrandService brandService;
	
	@BeforeEach
	public void setUp() {
		brandService = new BrandServiceImpl(brandRepository);
	}
	@Test
	public void testCreate() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1L);
		//when
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand brand2 = brandService.create(new Brand());
		//then
		assertEquals(1, brand2.getId());
		assertEquals("Apple", brand2.getName());
	}
	
	//jomrers ti2
//	@Test
//	public void TestCreat() {
//		//given
//		Brand brand = new Brand();
//		brand.setName("Apple");
//		//when
//		brandService.create(brand);
//		//then
//		verify(brandRepository,times(1)).save(brand);
//	}
	
	@Test
	public void testGetByIdTrue() {
		//given
			Brand brand = new Brand();
			brand.setName("apple");
			brand.setId(1L);
		//when
		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1L);
		//then
		assertEquals(1, brandReturn.getId());
		assertEquals("apple", brandReturn.getName());
		
	}
	
	@Test
	public void testGetByIdFalse() {
		//given
		
		//when
			when(brandRepository.findById(2L)).thenReturn(Optional.empty());
			assertThatThrownBy(()-> brandService.getById(2L))
				.isInstanceOf(ResourceNotFoundException.class);
				
			
		//then
	}
	
	
}
