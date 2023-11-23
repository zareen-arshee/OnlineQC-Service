package com.inadev.qms.onlineqc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="onlineqc_record_field")
public class OnlineQcRecordField extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "onlineqc_record_data_id", referencedColumnName = "onlineqc_record_data_id")
	//@ManyToOne
    private Long onlineQcRecordDataId;
	
	@JoinColumn(name = "onlineqc_record_id", referencedColumnName = "onlineqc_record_id")
	//@ManyToOne
    private Long onlineQcRecordId;
	
	@JoinColumn(name = "onlineqc_record_date_id", referencedColumnName = "onlineqc_record_date_id")
	//@ManyToOne
    private Long onlineQcRecordDateId;
	
//	@JsonFormat(pattern = "dd-MM-yyyy")
//	@Column(name = "date_of_entry")
//	//@CreationTimestamp
//	private Date dateOfEntry;
	
	private String fieldName;
	
	private String fieldValue;

}

