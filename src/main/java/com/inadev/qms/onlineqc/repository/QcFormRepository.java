package com.inadev.qms.onlineqc.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inadev.qms.onlineqc.entity.QcForm;
import com.inadev.qms.onlineqc.enums.CommonStatus;

public interface QcFormRepository extends JpaRepository<QcForm, Long>{

	QcForm findFormById(Long id);

	QcForm findQcFormById(Long id);
	
	@Query(value = "select r FROM QcForm r where r.status IN ?1 AND r.oemCode =?2")
	List<QcForm> searchProductsWithStatus(CommonStatus status, String oemCode);

	@Query(value = "select r FROM QcForm r where r.formName LIKE %?1% AND r.status IN ?2 AND r.oemCode =?3")
	List<QcForm> searchProductsWithFormNameAndStatus(String formName, CommonStatus status, String oemCode);

	@Query(value = "select r FROM QcForm r where r.factoryName LIKE %?1% AND r.status IN ?2 AND r.oemCode =?3")
	List<QcForm> searchProductsWithFactoryNameAndStatus(String factoryName, CommonStatus status, String oemCode);

	@Query(value = "select r FROM QcForm r where r.formName LIKE %?1% AND r.factoryName LIKE %?2% AND r.status IN ?3 AND r.oemCode =?4")
	List<QcForm> searchProductsByAll(String formName, String factoryName, CommonStatus status, String oemCode);

	//boolean existsByFormName(String formName);

	QcForm findQcFormBySectionIdAndFormName( String sectionId, String formName);

	@Query(value = "select r FROM QcForm r where r.sectionId = ?1")
	List<QcForm> findAllBySectionId(String sectionId);

	@Query(value = "select * FROM qc_form where oem_code = ?1 AND section_id = ?2 AND form_name = ?3", nativeQuery = true)
	List<QcForm> findByOemCodeAndSectionIdAndFormName(String oemCode,String sectionId, String formName);

}
