package com.meng.java.phoneshopnight.phoneshopnight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.java.phoneshopnight.phoneshopnight.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String name);
}
