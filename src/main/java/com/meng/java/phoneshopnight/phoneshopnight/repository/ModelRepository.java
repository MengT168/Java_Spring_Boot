package com.meng.java.phoneshopnight.phoneshopnight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
	List<Model> findByBrandId(Long brandId);
}
