package com.inadev.qms.onlineqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.inadev.qms.onlineqc.entity.QcFormField;

public interface QcFormFieldRepository extends JpaRepository<QcFormField , String> {

	List<QcFormField> findByQcFormDataId(Long id);

	List<QcFormField> findQcFormFieldByQcFormDataId(Long qcFormDataId);

	QcFormField findQcFormFieldById(Long id);
	
//	@Modifying
//	@Query(value="Delete From qc_form_field Where qc_form_data_id=?1" , nativeQuery = true)
//	void deleteByQcFormDataId(Long qcFormDataId);


}
