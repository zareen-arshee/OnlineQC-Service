package com.inadev.qms.onlineqc.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Integer version;
	
	@Column(name = "update_by")
	private String updateBy;
	
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	
	@Column(name = "created_at",updatable = false)
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	
}
