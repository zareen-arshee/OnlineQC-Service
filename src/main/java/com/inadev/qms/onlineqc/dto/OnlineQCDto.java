package com.inadev.qms.onlineqc.dto;


import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlineQCDto {
	
	private Long id;
	private String sectionId;
	
	private String sectionName;
	
	private String oemId;
	
	private String oemName;
	
	private String factoryCode;
	
	private String factoryName;
	
	private String unitCode;
	
	private String unitName;
	
	private CommonStatus status;
	}
