package com.meng.java.phoneshopnight.phoneshopnight.util;

import org.springframework.data.domain.PageRequest;

public interface PageUtil {
	int DEFAULT_PAGE_LIMIT = 5;
	int DEFAULT_PAGE_NUMBER =  1;
	String PAGE_LIMIT = "_limit";
	String PAGE_NUMBER = "_page";
	
	static PageRequest getPageable(int pageNumber , int pageSize) {
		if(pageNumber < DEFAULT_PAGE_NUMBER) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		if(pageSize <1) {
			pageSize = DEFAULT_PAGE_LIMIT;
		}
		PageRequest pageable = PageRequest.of(pageNumber-1, pageSize);
		return pageable;
	}
}
