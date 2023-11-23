package com.inadev.qms.onlineqc.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineQcRecordDateDto {
	
	private Long id;
	
	private Long onlineQcRecordId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateOfEnrty;
	
	private List<OnlineQcRecordFieldDto> onlineQcRecordField;

}
