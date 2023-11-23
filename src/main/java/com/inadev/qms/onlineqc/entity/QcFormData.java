package com.inadev.qms.onlineqc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="qc_form_data")
public class QcFormData extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "qcform_id", referencedColumnName = "qcform_id")
	//@ManyToOne
    private Long qcFormId;
	
	private String uniqueId;
	
	private String fieldName;
	
	private String fieldType;
	
	private String noOfFields;
	
	private String displayFieldName;
	
	private String printTabularFormat;
	
	private String includeAsHeader;
	
	private String includeAsFooter;

}
