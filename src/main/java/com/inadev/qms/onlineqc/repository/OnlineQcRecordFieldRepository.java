package com.inadev.qms.onlineqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inadev.qms.onlineqc.entity.OnlineQcRecordField;

public interface OnlineQcRecordFieldRepository  extends JpaRepository<OnlineQcRecordField, Long>{

	List<OnlineQcRecordField> findByOnlineQcRecordDataId(Long id);

	List<OnlineQcRecordField> findByOnlineQcRecordDateId(Long id);

//	@Query(value = "SELECT * FROM onlineqc_record_field WHERE online_qc_record_date_id = ?1", nativeQuery = true)
//	List<OnlineQcRecordField> findByOnlineQcRecordDateIdAndFromDateAndToDate(Long id, String fromDate, String toDate);

}
