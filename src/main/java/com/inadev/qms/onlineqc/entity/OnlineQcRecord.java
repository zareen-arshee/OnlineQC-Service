package com.inadev.qms.onlineqc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name="online_qc_record")
public class OnlineQcRecord extends BaseEntity implements Serializable{
	
private static final long serialVersionUID = 2728528310673758160L;

	private String uniqueId;

	@NotNull
	@Column(name = "section_id")
	private String sectionId;
	
	@NotNull
	@Column(name = "section_name")
	private String sectionName;
	
	@Column(name = "factory_name")
	private String factoryName;
	
	@Column(name = "unit_name")
	private String unitName;
	
	@Column(name = "factory_address")
	private String factoryAddress;
	
	@Column(name = "form_name")
	private String formName;
	
	@Column(name = "information")
	private String information;
	
	@Column(name = "status")
	private CommonStatus status;
}
