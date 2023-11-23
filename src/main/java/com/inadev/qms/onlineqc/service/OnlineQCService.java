package com.inadev.qms.onlineqc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Convert;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inadev.qms.onlineqc.dto.OnlineQCDto;
import com.inadev.qms.onlineqc.dto.OnlineQcFormStructureDto;
import com.inadev.qms.onlineqc.dto.OnlineQcRecordDateDto;
import com.inadev.qms.onlineqc.dto.OnlineQcRecordDto;
import com.inadev.qms.onlineqc.dto.OnlineQcRecordFieldDto;
import com.inadev.qms.onlineqc.dto.QcFormDataDto;
import com.inadev.qms.onlineqc.dto.QcFormDto;
import com.inadev.qms.onlineqc.dto.QcFormFieldDto;
import com.inadev.qms.onlineqc.dto.QcFormListProjection;
import com.inadev.qms.onlineqc.dto.QcFormSearchRequestDto;
import com.inadev.qms.onlineqc.entity.OnlineQC;
import com.inadev.qms.onlineqc.entity.OnlineQcRecord;
import com.inadev.qms.onlineqc.entity.QcForm;
import com.inadev.qms.onlineqc.entity.QcFormData;
import com.inadev.qms.onlineqc.entity.QcFormField;
import com.inadev.qms.onlineqc.enums.CommonStatus;
import com.inadev.qms.onlineqc.repository.OnlineQCRepository;
import com.inadev.qms.onlineqc.repository.OnlineQcRecordDateRepository;
import com.inadev.qms.onlineqc.repository.OnlineQcRecordFieldRepository;
import com.inadev.qms.onlineqc.repository.OnlineQcRecordRepository;
import com.inadev.qms.onlineqc.repository.QcFormDataRepository;
import com.inadev.qms.onlineqc.repository.QcFormFieldRepository;
import com.inadev.qms.onlineqc.repository.QcFormRepository;
import com.inadev.qms.onlineqc.response.ResponseDTO;
import com.inadev.qms.onlineqc.entity.OnlineQcRecordDate;
import com.inadev.qms.onlineqc.entity.OnlineQcRecordField;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OnlineQCService {

	@Autowired
	OnlineQCRepository onlineQcRepository;

	@Autowired
	QcFormRepository qcFormRepository;

	@Autowired
	QcFormDataRepository qcFormDataRepository;

	@Autowired
	QcFormFieldRepository qcFormFieldRepository;

	@Autowired
	OnlineQcRecordRepository onlineQcRecordRepository;

	@Autowired
	OnlineQcRecordFieldRepository onlineQcRecordFieldRepository;
	
	@Autowired
	OnlineQcRecordDateRepository onlineQcRecordDateRepository;

	@Autowired
	private ModelMapper modelMapper;
	
		public ResponseDTO<OnlineQC> save(OnlineQCDto onlineQcDto) throws Exception{
			OnlineQC onlineQC = convert(onlineQcDto);
			onlineQC = onlineQcRepository.save(onlineQC);
			return new ResponseDTO<OnlineQC>(onlineQC, null, null, null);
		}
		
		private OnlineQC convert(OnlineQCDto onlineQcDto) {
			OnlineQC onlineQC = new OnlineQC();
			onlineQC.setSectionId(onlineQcDto.getSectionId());
			onlineQC.setSectionName(onlineQcDto.getSectionName());
			onlineQC.setFactoryCode(onlineQcDto.getFactoryCode());
			onlineQC.setFactoryName(onlineQcDto.getFactoryName());
			onlineQC.setOemId(onlineQcDto.getOemId());
			onlineQC.setOemName(onlineQcDto.getOemName());
			onlineQC.setUnitCode(onlineQcDto.getUnitCode());
			onlineQC.setUnitName(onlineQcDto.getUnitName());
			onlineQC.setStatus(onlineQcDto.getStatus());
			return onlineQC;
			
		}
	
		public OnlineQC addNewSection(OnlineQCDto onlineQcDto) {
		log.info("UserService : addNewUser()");

		OnlineQC onlineQC = new OnlineQC();
		/**/
		onlineQC = modelMapper.map(onlineQcDto, OnlineQC.class);

		onlineQC.setSectionId(onlineQcDto.getSectionId());
		onlineQC.setSectionName(onlineQcDto.getSectionName());
		onlineQC.setFactoryCode(onlineQcDto.getFactoryCode());
		onlineQC.setFactoryName(onlineQcDto.getFactoryName());
		onlineQC.setOemId(onlineQcDto.getOemId());
		onlineQC.setOemName(onlineQcDto.getOemName());
		onlineQC.setUnitCode(onlineQcDto.getUnitCode());
		onlineQC.setUnitName(onlineQcDto.getUnitName());
		onlineQC.setStatus(onlineQcDto.getStatus());

		onlineQcRepository.save(onlineQC);

		return onlineQC;
	}

	public boolean checkSectionExistById(String sectionId) {
		return onlineQcRepository.existsBySectionId(sectionId);

	}

	public List<OnlineQCDto> findAllSection() {
		log.info("OnlineQCService : findAllSection()");
		List<OnlineQC> sectionList = null;
		List<OnlineQCDto> sectionDtoList = new ArrayList<>();

		sectionList = onlineQcRepository.findAll();
		if (sectionList != null && !sectionList.isEmpty()) {
			for (OnlineQC section : sectionList) {
				OnlineQCDto sectionDto = modelMapper.map(section, OnlineQCDto.class);

				sectionDtoList.add(sectionDto);
			}
		}
		return sectionDtoList;
	}

	public OnlineQCDto findSectionById(Long id) {
		log.info("OnlineQCService : findSectionById()");

		OnlineQCDto onlineQcDto = null;

		OnlineQC onlineQc;
		try {
			onlineQc = onlineQcRepository.findSectionById(id);

			onlineQcDto = modelMapper.map(onlineQc, OnlineQCDto.class);

		} catch (Exception e) {
			log.error("OnlineQCService :findSectionById() : error", e.fillInStackTrace());

		}

		return onlineQcDto;

	}

	public OnlineQC updateSection(OnlineQCDto onlineQcDto) {
		log.info("ItemService : updateItem()");

		OnlineQC onlineQC = onlineQcRepository.findSectionById(onlineQcDto.getId());

		OnlineQC savedSection = null;

		try {

			onlineQC.setSectionId(onlineQcDto.getSectionId());
			onlineQC.setSectionName(onlineQcDto.getSectionName());
			onlineQC.setFactoryCode(onlineQcDto.getFactoryCode());
			onlineQC.setFactoryName(onlineQcDto.getFactoryName());
			onlineQC.setOemId(onlineQcDto.getOemId());
			onlineQC.setOemName(onlineQcDto.getOemName());
			onlineQC.setUnitCode(onlineQcDto.getUnitCode());
			onlineQC.setUnitName(onlineQcDto.getUnitName());
			onlineQC.setStatus(onlineQcDto.getStatus());

			savedSection = onlineQcRepository.save(onlineQC);

		} catch (Exception e) {

			log.error("OnlineQCService :updateSection() : error", e.fillInStackTrace());
		}
		return savedSection;
	}

	public List<OnlineQCDto> searchSection(String sectionId, String sectionName, CommonStatus status) {
		log.info("OnlineQCService : findItemById()");

		List<OnlineQC> sectionList = null;
		List<OnlineQCDto> sectionDtoList = new ArrayList<>();

		if (!sectionId.isEmpty() && !sectionName.isEmpty())
			sectionList = onlineQcRepository.searchSectionbyIdNameStatus(sectionId, sectionName, status);
		else if (sectionId.isEmpty() && !sectionName.isEmpty())
			sectionList = onlineQcRepository.searchSectionbyNameStatus(sectionName, status);
		else if (!sectionId.isEmpty() && sectionName.isEmpty())
			sectionList = onlineQcRepository.searchSectionbyIdStatus(sectionId, status);
		else
			sectionList = onlineQcRepository.searchSectionbyStatus(status);

		if (sectionList != null && !sectionList.isEmpty()) {
			for (OnlineQC section : sectionList) {
				OnlineQCDto sectionDto = modelMapper.map(section, OnlineQCDto.class);

				sectionDtoList.add(sectionDto);
			}
		}
		return sectionDtoList;
	}

	@Transactional
	public QcForm addNewQCForm(QcFormDto qcFormDto) throws Exception {

		QcForm qcForm = addNewQcForm(qcFormDto);
		qcForm = qcFormRepository.save(qcForm);
		saveQcFormData(qcFormDto.getQcFormData(), qcForm.getId());
		return qcForm;
	}

	@Transactional
	public List<QcFormData> saveQcFormData(List<QcFormDataDto> qcFormDataDtoList, Long qcFormId) {
		List<QcFormData> qcFormList = new ArrayList<>();
		List<QcFormField> qcFormFieldList = new ArrayList<>();
		for (QcFormDataDto qcFormDataDto : qcFormDataDtoList) {
			QcFormData qcFormData = new QcFormData();
			qcFormData.setQcFormId(qcFormId);
			qcFormData.setUniqueId(qcFormDataDto.getUniqueId());
			qcFormData.setFieldName(qcFormDataDto.getFieldName());
			qcFormData.setFieldType(qcFormDataDto.getFieldType());
			qcFormData.setNoOfFields(qcFormDataDto.getNoOfFields());
			qcFormData.setDisplayFieldName(qcFormDataDto.getDisplayFieldName());
			qcFormData.setPrintTabularFormat(qcFormDataDto.getPrintTabularFormat());
			qcFormData.setIncludeAsHeader(qcFormDataDto.getIncludeAsHeader());
			qcFormData.setIncludeAsFooter(qcFormDataDto.getIncludeAsFooter());

			qcFormData = qcFormDataRepository.save(qcFormData);

			for (QcFormFieldDto qcFormFieldDto : qcFormDataDto.getQcFormField()) {
				QcFormField qcFormField = new QcFormField();
				qcFormField.setQcFormDataId(qcFormData.getId());
				qcFormField.setFieldValue(qcFormFieldDto.getFieldValue());
				qcFormFieldList.add(qcFormField);
			}
			qcFormList.add(qcFormData);
		}
		qcFormFieldRepository.saveAll(qcFormFieldList);
		return qcFormList;
	}

	public QcForm addNewQcForm(QcFormDto qcFormDto) {
		log.info("OnlineQCService : addNewQcForm()");

		QcForm qcForm = new QcForm();
		/**/
		qcFormDto = modelMapper.map(qcFormDto, QcFormDto.class);

		qcForm.setSectionId(qcFormDto.getSectionId());
		qcForm.setSectionName(qcFormDto.getSectionName());
		qcForm.setFactoryName(qcFormDto.getFactoryName());
		qcForm.setUnitName(qcFormDto.getUnitName());
		qcForm.setFactoryAddress(qcFormDto.getFactoryAddress());
		qcForm.setFormName(qcFormDto.getFormName());
		qcForm.setOemCode(qcFormDto.getOemCode());
		qcForm.setInformation(qcFormDto.getInformation());
		qcForm.setStatus(qcFormDto.getStatus());

		return qcForm;
	}

	public List<QcFormDto> getAllForms() {
		log.info("OnlineQCService : getAllForms()");
		List<QcForm> formList = null;
		List<QcFormDto> formDtoList = new ArrayList<>();
		QcFormDto formMasterDto = new QcFormDto();
		formList = qcFormRepository.findAll();
		for (QcForm formMaster : formList) {
			formMasterDto = modelMapper.map(formMaster, QcFormDto.class);
			formDtoList.add(formMasterDto);
			List<QcFormData> qcFormDataList = qcFormDataRepository.findByQcFormId(formMaster.getId());
			List<QcFormDataDto> qcFormDataDtoList = new ArrayList<>();

			for (QcFormData qcFormDataMaster : qcFormDataList) {
				QcFormDataDto qcFormDataDto = new QcFormDataDto();
				qcFormDataDto.setUniqueId(qcFormDataMaster.getUniqueId());
				qcFormDataDto.setFieldName(qcFormDataMaster.getFieldName());
				qcFormDataDto.setFieldType(qcFormDataMaster.getFieldType());
				qcFormDataDto.setQcFormId(qcFormDataMaster.getQcFormId());
				qcFormDataDto.setNoOfFields(qcFormDataMaster.getNoOfFields());
				qcFormDataDto.setDisplayFieldName(qcFormDataMaster.getDisplayFieldName());
				qcFormDataDto.setPrintTabularFormat(qcFormDataMaster.getPrintTabularFormat());
				qcFormDataDto.setIncludeAsHeader(qcFormDataMaster.getIncludeAsHeader());
				qcFormDataDto.setIncludeAsFooter(qcFormDataMaster.getIncludeAsFooter());
				qcFormDataDto.setId(qcFormDataMaster.getId());

				qcFormDataDtoList.add(qcFormDataDto);
				formMasterDto.setQcFormData(qcFormDataDtoList);

				List<QcFormField> qcFormFieldList = qcFormFieldRepository.findByQcFormDataId(qcFormDataMaster.getId());

				List<QcFormFieldDto> qcFormFieldDtoList = new ArrayList<>();

				for (QcFormField qcFormFieldMaster : qcFormFieldList) {
					QcFormFieldDto qcFormFieldMasterDto = modelMapper.map(qcFormFieldMaster, QcFormFieldDto.class);
					qcFormFieldDtoList.add(qcFormFieldMasterDto);
					qcFormDataDto.setQcFormField(qcFormFieldDtoList);
				}
			}

		}
		return formDtoList;
	}

	public QcFormDto findFormById(Long id) {
		log.info("OnlineQCService : getFormById()");
		QcFormDto qcFormDto = null;

		QcForm qcForm;
		try {
			qcForm = qcFormRepository.findFormById(id);
			qcFormDto = modelMapper.map(qcForm, QcFormDto.class);

			List<QcFormData> qcFormDataList = qcFormDataRepository.findByQcFormId(qcForm.getId());
			List<QcFormDataDto> qcFormDataDtoList = new ArrayList<>();
			for (QcFormData qcFormDataMaster : qcFormDataList) {

				QcFormDataDto qcFormDataDto = new QcFormDataDto();
				qcFormDataDto.setUniqueId(qcFormDataMaster.getUniqueId());
				qcFormDataDto.setFieldName(qcFormDataMaster.getFieldName());
				qcFormDataDto.setQcFormId(qcFormDataMaster.getQcFormId());
				qcFormDataDto.setFieldType(qcFormDataMaster.getFieldType());
				qcFormDataDto.setNoOfFields(qcFormDataMaster.getNoOfFields());
				qcFormDataDto.setDisplayFieldName(qcFormDataMaster.getDisplayFieldName());
				qcFormDataDto.setPrintTabularFormat(qcFormDataMaster.getPrintTabularFormat());
				qcFormDataDto.setIncludeAsHeader(qcFormDataMaster.getIncludeAsHeader());
				qcFormDataDto.setIncludeAsFooter(qcFormDataMaster.getIncludeAsFooter());
				qcFormDataDto.setId(qcFormDataMaster.getId());

				qcFormDataDtoList.add(qcFormDataDto);

				List<QcFormField> qcFormFieldList = qcFormFieldRepository.findByQcFormDataId(qcFormDataMaster.getId());
				List<QcFormFieldDto> qcFormFieldDtoList = new ArrayList<>();
				for (QcFormField qcFormFieldMaster : qcFormFieldList) {
					QcFormFieldDto qcFormFieldMasterDto = modelMapper.map(qcFormFieldMaster, QcFormFieldDto.class);
					qcFormFieldDtoList.add(qcFormFieldMasterDto);
					qcFormDataDto.setQcFormField(qcFormFieldDtoList);
				}
			}
			qcFormDto.setQcFormData(qcFormDataDtoList);
		} catch (Exception e) {
			log.error("OnlineQCService :getFormById() : error", e.fillInStackTrace());
		}
		return qcFormDto;
	}

	@Transactional
	public QcForm updateQcForm(@Valid QcFormDto qcFormDto) {
		log.info("OnlineQCService : editQcForm()");

		QcForm qcForm = qcFormRepository.findQcFormById(qcFormDto.getId());
		QcForm savedQcForm = null;

		try {
			qcForm.setId(qcFormDto.getId());
			qcForm.setFormName(qcFormDto.getFormName());
			qcForm.setSectionId(qcFormDto.getSectionId());
			qcForm.setSectionName(qcFormDto.getSectionName());
			qcForm.setInformation(qcFormDto.getInformation());
			qcForm.setFactoryName(qcFormDto.getFactoryName());
			qcForm.setUnitName(qcFormDto.getUnitName());
			qcForm.setFactoryAddress(qcFormDto.getFactoryAddress());
			qcForm.setStatus(qcFormDto.getStatus());

			savedQcForm = qcFormRepository.save(qcForm);
			updateQcFormDataList(qcFormDto.getQcFormData(), qcForm.getId());
		} catch (Exception e) {

			log.error("OnlineQCService :editQcForm() : error", e.fillInStackTrace());
		}
		return savedQcForm;
	}

	@Transactional
	private List<QcFormData> updateQcFormDataList(List<QcFormDataDto> qcFormDataDtoList, Long qcFormId) {
		List<QcFormData> QcFormIdList = qcFormDataRepository.findQcFormDataByQcFormId(qcFormId);
		List<QcFormField> qcFormDataIdQcFormFieldList = new ArrayList<QcFormField>();
		// qcFormDataRepository.deleteByQcFormId(qcFormId);

		for (QcFormDataDto i : qcFormDataDtoList) {
			if (i.getId() != null) {
				QcFormData qcFormData = qcFormDataRepository.findQcFormDataById(i.getId());
				qcFormData.setUniqueId(qcFormData.getUniqueId());
				qcFormData.setFieldName(i.getFieldName());
				qcFormData.setFieldType(i.getFieldType());
				qcFormData.setNoOfFields(i.getNoOfFields());
				qcFormData.setDisplayFieldName(i.getDisplayFieldName());
				qcFormData.setPrintTabularFormat(i.getPrintTabularFormat());
				qcFormData.setIncludeAsHeader(i.getIncludeAsHeader());
				qcFormData.setIncludeAsFooter(i.getIncludeAsFooter());
				qcFormData.setId(i.getId());

				updateQcFormFieldList(i.getQcFormField(), qcFormData.getId());
			} else {

				QcFormData newQcFormData = new QcFormData();
				newQcFormData.setQcFormId(qcFormId);
				newQcFormData.setUniqueId(i.getUniqueId());
				newQcFormData.setFieldName(i.getFieldName());
				newQcFormData.setFieldType(i.getFieldType());
				newQcFormData.setNoOfFields(i.getNoOfFields());
				newQcFormData.setDisplayFieldName(i.getDisplayFieldName());
				newQcFormData.setPrintTabularFormat(i.getPrintTabularFormat());
				newQcFormData.setIncludeAsHeader(i.getIncludeAsHeader());
				newQcFormData.setIncludeAsFooter(i.getIncludeAsFooter());
				newQcFormData.setId(i.getId());

				newQcFormData = qcFormDataRepository.save(newQcFormData);
				for (QcFormFieldDto j : i.getQcFormField()) {
					QcFormField newQcFormField = new QcFormField();
					newQcFormField.setQcFormDataId(newQcFormData.getId());
					newQcFormField.setFieldValue(j.getFieldValue());
					qcFormDataIdQcFormFieldList.add(newQcFormField);
				}

				QcFormIdList.add(newQcFormData);
			}
		}
		qcFormFieldRepository.saveAll(qcFormDataIdQcFormFieldList);
		return QcFormIdList;
	}

	@Transactional
	private List<QcFormField> updateQcFormFieldList(List<QcFormFieldDto> qcFormFieldList, Long qcFormDataId) {

		List<QcFormField> qcFormDataIdQcFormDataList = qcFormFieldRepository
				.findQcFormFieldByQcFormDataId(qcFormDataId);
		for (QcFormFieldDto i : qcFormFieldList) {

			if (i.getId() != null) {
				QcFormField qcFormField = qcFormFieldRepository.findQcFormFieldById(i.getId());
				qcFormField.setFieldValue(i.getFieldValue());
			} else {
				QcFormField newQcFormField = new QcFormField();
				newQcFormField.setQcFormDataId(qcFormDataId);
				newQcFormField.setFieldValue(i.getFieldValue());
				qcFormDataIdQcFormDataList.add(newQcFormField);
			}
		}
		// qcFormFieldRepository.deleteByQcFormDataId(qcFormDataId);
		qcFormFieldRepository.saveAll(qcFormDataIdQcFormDataList);
		return qcFormDataIdQcFormDataList;
	}

	public List<QcFormListProjection> searchQcForm(QcFormSearchRequestDto searchForm) {

		log.info("OnlineQCService : searchForm()");
		System.out.println("search result :" + searchForm);
		List<QcForm> searchResults = null;

		if (searchForm.getFormName() == "" && searchForm.getFactoryName() == ""
				|| searchForm.getFormName() == null && searchForm.getFactoryName() == null) {

			searchResults = qcFormRepository.searchProductsWithStatus(searchForm.getStatus(), searchForm.getOemCode());
		} else if (searchForm.getFormName() != "" && searchForm.getFactoryName() == ""
				|| searchForm.getFormName() != null && searchForm.getFactoryName() == null) {

			searchResults = qcFormRepository.searchProductsWithFormNameAndStatus(searchForm.getFormName(),
					searchForm.getStatus(), searchForm.getOemCode());
		} else if (searchForm.getFormName() == "" && searchForm.getFactoryName() != ""
				|| searchForm.getFormName() == null && searchForm.getFactoryName() != null) {

			searchResults = qcFormRepository.searchProductsWithFactoryNameAndStatus(searchForm.getFactoryName(),
					searchForm.getStatus(), searchForm.getOemCode());
		} else {
			searchResults = qcFormRepository.searchProductsByAll(searchForm.getFormName(), searchForm.getFactoryName(),
					searchForm.getStatus(), searchForm.getOemCode());
		}

		List<QcFormListProjection> projectResultList = null;
		if (searchResults != null && !searchResults.isEmpty()) {
			projectResultList = mapList(searchResults, QcFormListProjection.class);
		}

		return projectResultList;
	}

	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

	public List<QcFormDto> findQcFormDetailsBySectionId(String sectionId) {
		log.info("OnlineQCService : findQcFormDetailsBySectionId()");

		List<QcFormDto> qcFormDtoList = new ArrayList<>();
		List<QcForm> qcFormList = null;
		qcFormList = qcFormRepository.findAllBySectionId(sectionId);
		for (QcForm qcForm : qcFormList) {
			QcFormDto qcFormDto = new QcFormDto();
			qcFormDto.setId(qcForm.getId());
			qcFormDto.setSectionId(qcForm.getSectionId());
			qcFormDto.setSectionName(qcForm.getSectionName());
			qcFormDto.setFactoryName(qcForm.getFactoryName());
			qcFormDto.setUnitName(qcForm.getUnitName());
			qcFormDto.setFactoryAddress(qcForm.getFactoryAddress());
			qcFormDto.setFormName(qcForm.getFormName());
			qcFormDto.setInformation(qcForm.getInformation());
			qcFormDto.setStatus(qcForm.getStatus());

			qcFormDtoList.add(qcFormDto);

			List<QcFormData> qcFormDataList = qcFormDataRepository.findByQcFormId(qcForm.getId());
			List<QcFormDataDto> qcFormDataDtoList = new ArrayList<>();
			for (QcFormData qcFormDataMaster : qcFormDataList) {

				QcFormDataDto qcFormDataDto = new QcFormDataDto();
				qcFormDataDto.setUniqueId(qcFormDataMaster.getUniqueId());
				qcFormDataDto.setFieldName(qcFormDataMaster.getFieldName());
				qcFormDataDto.setQcFormId(qcFormDataMaster.getQcFormId());
				qcFormDataDto.setFieldType(qcFormDataMaster.getFieldType());
				qcFormDataDto.setNoOfFields(qcFormDataMaster.getNoOfFields());
				qcFormDataDto.setDisplayFieldName(qcFormDataMaster.getDisplayFieldName());
				qcFormDataDto.setPrintTabularFormat(qcFormDataMaster.getPrintTabularFormat());
				qcFormDataDto.setIncludeAsHeader(qcFormDataMaster.getIncludeAsHeader());
				qcFormDataDto.setIncludeAsFooter(qcFormDataMaster.getIncludeAsFooter());
				qcFormDataDto.setId(qcFormDataMaster.getId());

				qcFormDataDtoList.add(qcFormDataDto);

				List<QcFormField> qcFormFieldList = qcFormFieldRepository.findByQcFormDataId(qcFormDataMaster.getId());
				List<QcFormFieldDto> qcFormFieldDtoList = new ArrayList<>();
				for (QcFormField qcFormFieldMaster : qcFormFieldList) {
					QcFormFieldDto qcFormFieldMasterDto = modelMapper.map(qcFormFieldMaster, QcFormFieldDto.class);
					qcFormFieldDtoList.add(qcFormFieldMasterDto);
					qcFormDataDto.setQcFormField(qcFormFieldDtoList);
				}
			}
			qcFormDto.setQcFormData(qcFormDataDtoList);
		}
		return qcFormDtoList;
	}

	@Transactional
	public OnlineQcRecord addNewQcRecord(@Valid OnlineQcFormStructureDto onlineQcFormStructureDto) {
		OnlineQcRecord onlineQcRecord = saveNewOnlineQcRecord(onlineQcFormStructureDto);
		onlineQcRecord = onlineQcRecordRepository.save(onlineQcRecord);
		saveOnlineQcRecordDate(onlineQcFormStructureDto.getOnlineQcRecordDate(), onlineQcRecord.getId());
		return onlineQcRecord;
	}

	@Transactional
	public List<OnlineQcRecordDate> saveOnlineQcRecordDate(List<OnlineQcRecordDateDto> onlineQcRecordDateDtoList,
			Long onlineQcRecordId) {
		List<OnlineQcRecordDate> onlineQcRecordDateList = new ArrayList<>();
		List<OnlineQcRecordField> onlineQcRecordFieldList = new ArrayList<>();

			for (OnlineQcRecordDateDto onlineQcRecordDateDto : onlineQcRecordDateDtoList) {
				OnlineQcRecordDate onlineQcRecordDate = new OnlineQcRecordDate();
				onlineQcRecordDate.setOnlineQcRecordId(onlineQcRecordId);
				onlineQcRecordDate.setDateOfEntry(onlineQcRecordDateDto.getDateOfEnrty());
				onlineQcRecordDateList.add(onlineQcRecordDate);
				
				onlineQcRecordDate = onlineQcRecordDateRepository.save(onlineQcRecordDate);
				
				for (OnlineQcRecordFieldDto onlineQcRecordFieldDto : onlineQcRecordDateDto.getOnlineQcRecordField()) {
					OnlineQcRecordField onlineQcRecordField = new OnlineQcRecordField();
					onlineQcRecordField.setOnlineQcRecordDateId(onlineQcRecordDate.getId());
					onlineQcRecordField.setOnlineQcRecordId(onlineQcRecordId);
					onlineQcRecordField.setFieldName(onlineQcRecordFieldDto.getFieldName());
					onlineQcRecordField.setFieldValue(onlineQcRecordFieldDto.getFieldValue());
					onlineQcRecordFieldList.add(onlineQcRecordField);
				}
				onlineQcRecordDateList.add(onlineQcRecordDate);
		}
		onlineQcRecordFieldRepository.saveAll(onlineQcRecordFieldList);
		return onlineQcRecordDateList;
	}

	public OnlineQcRecord saveNewOnlineQcRecord(OnlineQcFormStructureDto onlineQcFormStructureDto) {
		log.info("OnlineQCService : saveNewOnlineQcRecord()");

		OnlineQcRecord onlineQcRecord = new OnlineQcRecord();
		onlineQcFormStructureDto = modelMapper.map(onlineQcFormStructureDto, OnlineQcFormStructureDto.class);
		onlineQcRecord.setUniqueId(onlineQcFormStructureDto.getUniqueId());
		onlineQcRecord.setSectionId(onlineQcFormStructureDto.getSectionId());
		onlineQcRecord.setSectionName(onlineQcFormStructureDto.getSectionName());
		onlineQcRecord.setFactoryName(onlineQcFormStructureDto.getFactoryName());
		onlineQcRecord.setUnitName(onlineQcFormStructureDto.getUnitName());
		onlineQcRecord.setFactoryAddress(onlineQcFormStructureDto.getFactoryAddress());
		onlineQcRecord.setFormName(onlineQcFormStructureDto.getFormName());
		onlineQcRecord.setInformation(onlineQcFormStructureDto.getInformation());
		onlineQcRecord.setStatus(onlineQcFormStructureDto.getStatus());

		return onlineQcRecord;
	}

	public List<OnlineQcRecordDto> getOnlineQcRecord(String sectionId, String factoryName, String unitName,
			String formName, String fromDate, String toDate) {
		List<OnlineQcRecordDto> onlineQcRecordDtoList = new ArrayList<>();
		List<OnlineQcRecord> onlineQcRecordList = null;
		onlineQcRecordList = onlineQcRecordRepository.findAllBySectionIdFactoryNameUnitNameFormName(
				sectionId, factoryName, unitName, formName);

		for (OnlineQcRecord onlineQcRecord : onlineQcRecordList) {
			OnlineQcRecordDto onlineQcRecordDto = new OnlineQcRecordDto();
			onlineQcRecordDto.setId(onlineQcRecord.getId());
			onlineQcRecordDto.setSectionId(onlineQcRecord.getSectionId());
			onlineQcRecordDto.setUniqueId(onlineQcRecord.getUniqueId());
			onlineQcRecordDto.setSectionName(onlineQcRecord.getSectionName());
			onlineQcRecordDto.setFactoryName(onlineQcRecord.getFactoryName());
			onlineQcRecordDto.setUnitName(onlineQcRecord.getUnitName());
			onlineQcRecordDto.setFactoryAddress(onlineQcRecord.getFactoryAddress());
			onlineQcRecordDto.setFormName(onlineQcRecord.getFormName());
			onlineQcRecordDto.setInformation(onlineQcRecord.getInformation());
			onlineQcRecordDto.setStatus(onlineQcRecord.getStatus());

			List<OnlineQcRecordDateDto> onlineQcRecordDateDtoList = new ArrayList<>();
				
				List<OnlineQcRecordDate> onlineQcRecordDateList = onlineQcRecordDateRepository
						.findByOnlineQcRecordId(onlineQcRecord.getId(), fromDate, toDate);
				
				for (OnlineQcRecordDate onlineQcRecordDateMaster : onlineQcRecordDateList) {
					OnlineQcRecordDateDto onlineQcRecordDateDto = new OnlineQcRecordDateDto();
					onlineQcRecordDateDto.setId(onlineQcRecordDateMaster.getId());
					onlineQcRecordDateDto.setOnlineQcRecordId(onlineQcRecordDateMaster.getOnlineQcRecordId());;
					onlineQcRecordDateDto.setDateOfEnrty(onlineQcRecordDateMaster.getDateOfEntry());

					onlineQcRecordDateDtoList.add(onlineQcRecordDateDto);
					
					List<OnlineQcRecordField> onlineQcRecordFieldList = onlineQcRecordFieldRepository
							.findByOnlineQcRecordDateId(onlineQcRecordDateMaster.getId());
					List<OnlineQcRecordFieldDto> onlineQcRecordFieldDtoList = new ArrayList<>();
					for (OnlineQcRecordField onlineQcRecordFieldMaster : onlineQcRecordFieldList) {
						OnlineQcRecordFieldDto OnlineQcRecordFieldMasterDto = modelMapper.map(onlineQcRecordFieldMaster,
								OnlineQcRecordFieldDto.class);
						onlineQcRecordFieldDtoList.add(OnlineQcRecordFieldMasterDto);
					}
					onlineQcRecordDateDto.setOnlineQcRecordField(onlineQcRecordFieldDtoList);
				}
					onlineQcRecordDto.setOnlineQcRecordDate(onlineQcRecordDateDtoList);
			if(onlineQcRecordDateDtoList.size()!=0) {
				onlineQcRecordDtoList.add(onlineQcRecordDto);
			}
		}
		return onlineQcRecordDtoList;
	}

	public List<QcForm> checkQcFormExistByOemCodeAndSectionIdAndFormName(String oemCode, String sectionId, String formName) {
		List<QcForm> qcForm = qcFormRepository.findByOemCodeAndSectionIdAndFormName(oemCode,sectionId, formName);
		return qcForm;
	}

}
