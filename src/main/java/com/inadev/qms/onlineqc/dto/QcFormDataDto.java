package com.inadev.qms.onlineqc.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QcFormDataDto {
	
	private Long id;
	
	private Long qcFormId;
	
	private String uniqueId;
	
	private String fieldName;
	
	private String fieldType;
	
	private String noOfFields;
	
	private String displayFieldName;
	
	private String printTabularFormat;
	
	private String includeAsHeader;
	
	private String includeAsFooter;
	
	private List<QcFormFieldDto> qcFormField;

}
