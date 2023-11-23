package com.inadev.qms.onlineqc.dto;

import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
	
	private String sectionId;
	
	private String sectionName;
	
	private CommonStatus status;

}
