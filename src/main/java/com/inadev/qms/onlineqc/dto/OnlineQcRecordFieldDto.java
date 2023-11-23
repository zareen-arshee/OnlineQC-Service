package com.inadev.qms.onlineqc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineQcRecordFieldDto {
	
	private Long id;
	
	private Long onlineQcRecordDateId;
	
	private String fieldName;
	
	private String fieldValue;

}
