package com.inadev.qms.onlineqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.inadev.qms.onlineqc.entity.QcFormData;

public interface QcFormDataRepository extends JpaRepository<QcFormData, String> {

	List<QcFormData> findByQcFormId(Long id);

	List<QcFormData> findQcFormDataByQcFormId(Long qcFormId);

	QcFormData findQcFormDataById(Long id);
	
//	@Modifying
//	@Query(value="Delete From qc_form_data Where qc_form_id=?1" , nativeQuery = true)
//	void deleteByQcFormId(Long qcFormId);


}
