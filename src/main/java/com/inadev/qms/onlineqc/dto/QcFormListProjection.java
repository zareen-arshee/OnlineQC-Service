package com.inadev.qms.onlineqc.dto;

import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QcFormListProjection {
	
	private String factoryName;
	private String factoryAddress;
	private String formName;
	private CommonStatus status;

}
