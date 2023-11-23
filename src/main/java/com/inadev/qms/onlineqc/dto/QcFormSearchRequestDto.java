package com.inadev.qms.onlineqc.dto;

import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QcFormSearchRequestDto {
	
	private String formName;
	private String factoryName;
	private CommonStatus status;
	private String oemCode;

}
