package com.inadev.qms.onlineqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inadev.qms.onlineqc.entity.OnlineQcRecordDate;

public interface OnlineQcRecordDateRepository  extends JpaRepository<OnlineQcRecordDate, Long>{

	//List<OnlineQcRecordDate> findByOnlineQcRecordDataId(Long id);

	//select * from onlineqc_record_data_field Where 
	//date_of_entry BETWEEN '2023-07-31 00:00:00' AND '2023-08-01 05:30:00' and record_id IN();
	
//	@Query(value = "SELECT * FROM onlineqc_record_date u "
//			+ "JOIN onlineqc_record_field a ON u.id = a.online_qc_record_date_id "
//			+ "WHERE u.online_qc_record_data_id = ?1 AND u.date_of_entry BETWEEN ?2 AND ?3", nativeQuery = true)
//	List<OnlineQcRecordDate> findByOnlineQcRecordDataId(Long id, String fromDate, String toDate);
	
//	@Query(value = "SELECT * FROM onlineqc_record_date WHERE online_qc_record_data_id = ?1 AND date_of_entry BETWEEN ?2 AND ?3", nativeQuery = true)
//	List<OnlineQcRecordDate> findByOnlineQcRecordDataId(Long id, String fromDate, String toDate);

	@Query(value = "SELECT * FROM onlineqc_record_date WHERE online_qc_record_id = ?1 AND date_of_entry BETWEEN ?2 AND ?3", nativeQuery = true)
	List<OnlineQcRecordDate> findByOnlineQcRecordId(Long id, String fromDate, String toDate);

}
