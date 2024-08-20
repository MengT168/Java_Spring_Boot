package com.meng.java.phoneshopnight.phoneshopnight.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
	
	private ProductImportHistoryFilter productImportHistoryFilter;

	@Override
	public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();	
		
		if(Objects.nonNull(productImportHistoryFilter.getStartDate()) ) {
			Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(importHistory.get("dateImport"), productImportHistoryFilter.getStartDate());
			predicates.add(startDate);
		}
		if(Objects.nonNull(productImportHistoryFilter.getStartDate()) ) {
			Predicate endDate = criteriaBuilder.lessThanOrEqualTo(importHistory.get("dateImport"), productImportHistoryFilter.getEndDate());
			predicates.add(endDate);
		}
		    
		    Predicate predicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
		    return predicate;
	}
	}


