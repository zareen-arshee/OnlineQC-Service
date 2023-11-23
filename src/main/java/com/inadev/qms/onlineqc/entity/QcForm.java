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
@Table(name="qc_form")
public class QcForm extends BaseEntity implements Serializable{
	
private static final long serialVersionUID = 2728528310673758160L;
	
	@NotNull
	private String sectionId;
	
	@NotNull
	private String sectionName;
	
	private String factoryName;
	
	private String unitName;
	
	private String factoryAddress;
	
	private String formName;
	
	private String oemCode;
	
	private String information;
	
	private CommonStatus status;

}
