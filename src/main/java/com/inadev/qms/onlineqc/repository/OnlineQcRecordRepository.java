package com.inadev.qms.onlineqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inadev.qms.onlineqc.entity.OnlineQcRecord;

public interface OnlineQcRecordRepository extends JpaRepository<OnlineQcRecord, Long>{

	@Query(value = "SELECT * FROM online_qc_record WHERE section_id = ?1 AND factory_name = ?2 AND unit_name = ?3 AND form_name = ?4", nativeQuery = true)
	List<OnlineQcRecord> findAllBySectionIdFactoryNameUnitNameFormName(String sectionId, String factoryName, String unitName, String formName);
	
//	@Query(value = "Select * FROM online_qc_record u JOIN onlineqc_record_data a ON u.id = a.online_qc_record_id "
//			+ "JOIN onlineqc_record_date b ON u.id = b.online_qc_record_id "
//			+ "JOIN onlineqc_record_field c ON u.id = c.online_qc_record_id "
//			+ "WHERE u.section_id = ?1 AND u.factory_name = ?2 AND u.unit_name = ?3 AND u.form_name = ?4 AND b.date_of_entry BETWEEN ?5 AND ?6", nativeQuery = true)
//	List<OnlineQcRecord>findAllBySectionIdFactoryNameUnitNameFormNameFromDateAndToDate(String sectionId, String factoryName, String unitName, String formName, String fromDate, String toDate);
}
