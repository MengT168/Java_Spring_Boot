package com.meng.java.phoneshopnight.phoneshopnight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> , JpaSpecificationExecutor<Color> {


}
