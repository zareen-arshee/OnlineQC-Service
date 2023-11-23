package com.inadev.qms.onlineqc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QcFormFieldDto {
	
	private Long id;
	
	private Long qcFormDataId;
	
	private String fieldValue;

}
