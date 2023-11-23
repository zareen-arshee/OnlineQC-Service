package com.inadev.qms.onlineqc.dto;

import java.util.List;

import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QcFormDto {

	private Long id;
	
	private String sectionId;
	
	private String sectionName;
	
	private String factoryName;
	
	private String unitName;
	
	private String factoryAddress;
	
	private String formName;
	
	private String oemCode;
	
	private String information;
	
	private CommonStatus status;
	
	private List<QcFormDataDto> qcFormData;
}
