package com.meng.java.phoneshopnight.phoneshopnight.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;
@Data
public class PageDTO {
	
	private List<?> list;
	private PaginationDTO pagination;
	
	public PageDTO(Page<?> page) {
		this.list = page.getContent();
		this.pagination = PaginationDTO.builder()
				.empty(page.isEmpty())
				.first(page.isFirst())
				.last(page.isLast())
				.pageSize(page.getPageable().getPageSize())
				.pageNumber(page.getPageable().getPageNumber()+1)
				.totalPages(page.getTotalPages())
				.totalElement(page.getTotalElements())
				.numberOfElement(page.getNumberOfElements())
				.build();
				
	}
}
