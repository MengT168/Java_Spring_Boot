package com.meng.java.phoneshopnight.phoneshopnight.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;

import lombok.Data;

@Data
public class BrandSpec implements Specification<Brand> {

	private final BrandFilter brandFilter;
	
	List<Predicate> predicates = new ArrayList<>();
	
	@Override
	public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(brandFilter.getName() != null) {
//			Predicate namefilter = brand.get("name").in(brandFilter.getName());
//			predicates.add(namefilter);
			Predicate namefilter = cb.like(cb.upper(brand.get("name")), "%"+brandFilter.getName().toUpperCase()+"%");
			predicates.add(namefilter);
		}
		
		if(brandFilter.getId() != null) {
			Predicate id = brand.get("id").in(brandFilter.getId());
			predicates.add(id);
		}
		
//		Predicate[] pp = predicates.toArray(new Predicate[0]);
//		return cb.and(predicates.toArray(new Predicate[0]));
		return cb.and(predicates.toArray(Predicate[]::new));
	}

}
