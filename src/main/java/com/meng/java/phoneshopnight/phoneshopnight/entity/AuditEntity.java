package com.meng.java.phoneshopnight.phoneshopnight.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditEntity {
	@CreatedDate
	private LocalDateTime createDate;
	@LastModifiedDate
	private LocalDateTime updateDate;
	@CreatedBy
	private String createUser;
	@LastModifiedBy
	private String updateUser;
}
