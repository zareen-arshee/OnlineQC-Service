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
@Table(name="qc_form_field")
public class QcFormField extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "qcform_data_id", referencedColumnName = "qcform_data_id")
	//@ManyToOne
    private Long qcFormDataId;
	
	private String fieldValue;

}
