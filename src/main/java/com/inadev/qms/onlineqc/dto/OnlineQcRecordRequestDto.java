package com.inadev.qms.onlineqc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineQcRecordRequestDto {
	
	private String sectionId;
	
	private String factoryName;
	
	private String unitName;
	
	private String formName;
	
	private String fromDate;
	
	private String toDate;

}
