package com.inadev.qms.onlineqc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="onlineQC")
public class OnlineQC extends BaseEntity implements Serializable{
	
private static final long serialVersionUID = 2728528310673758160L;
	
	@NotNull
	private String sectionId;
	
	@NotNull
	private String sectionName;
	
	private String oemId;
	
	private String oemName;
	
	private String factoryCode;
	
	private String factoryName;
	
	private String unitCode;
	
	private String unitName;
	
	private CommonStatus status;

	}
	
