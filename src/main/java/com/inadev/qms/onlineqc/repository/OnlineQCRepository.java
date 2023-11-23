package com.inadev.qms.onlineqc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inadev.qms.onlineqc.entity.OnlineQC;
import com.inadev.qms.onlineqc.enums.CommonStatus;

@Repository
public interface OnlineQCRepository extends JpaRepository<OnlineQC, Long>{
	
	boolean existsBySectionId(String sectionId);
	
	OnlineQC findSectionById(Long id);
	
	@Query(value = "select r FROM OnlineQC r where r.sectionId like %?1% AND r.sectionName like %?2% AND r.status=?3")
	List<OnlineQC> searchSectionbyIdNameStatus(String id, String name, CommonStatus status);
	
	@Query(value = "select r FROM OnlineQC r where r.sectionId like %?1% AND r.status=?2")
	List<OnlineQC> searchSectionbyIdStatus(String id, CommonStatus status);

	@Query(value = "select r FROM OnlineQC r where r.sectionName like %?1% AND r.status=?2")
	List<OnlineQC> searchSectionbyNameStatus(String name, CommonStatus status);
	
	@Query(value = "select r FROM OnlineQC r where r.status=?1")
	List<OnlineQC> searchSectionbyStatus(CommonStatus status);
	
}
