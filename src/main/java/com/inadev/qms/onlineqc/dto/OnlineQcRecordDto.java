package com.inadev.qms.onlineqc.dto;

import java.util.List;

import com.inadev.qms.onlineqc.enums.CommonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlineQcRecordDto {
	
	private Long id;
	
	private String uniqueId;
	
	private String sectionId;
	
	private String sectionName;
	
	private String factoryName;
	
	private String unitName;
	
    private String factoryAddress;
	
	private String formName;
	
	private String information;
	
	private CommonStatus status;
	
	private List<OnlineQcRecordDateDto> onlineQcRecordDate;
}

