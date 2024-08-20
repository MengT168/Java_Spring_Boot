package com.meng.java.phoneshopnight.phoneshopnight.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Sale;
import com.meng.java.phoneshopnight.phoneshopnight.entity.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {

	private SaleDetailFilter detailFilter;
//	private List<Predicate> predicates = new ArrayList<>();
	
	@Override
	public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();	
		Join<SaleDetail, Sale> sale = saleDetail.join("sale");
		
		if(Objects.nonNull(detailFilter.getStartDate()) ) {
			criteriaBuilder.greaterThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());
		}
		if(Objects.nonNull(detailFilter.getStartDate()) ) {
			criteriaBuilder.lessThanOrEqualTo(sale.get("soldDate"), detailFilter.getEndDate());
		}
			//get status true only
		  predicates.add(criteriaBuilder.isTrue(sale.get("status")));
		    
		    Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		    return predicate;
	}

}
