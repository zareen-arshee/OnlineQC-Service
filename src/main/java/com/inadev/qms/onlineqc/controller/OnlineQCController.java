package com.inadev.qms.onlineqc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inadev.qms.onlineqc.dto.OnlineQCDto;
import com.inadev.qms.onlineqc.dto.OnlineQcFormStructureDto;
import com.inadev.qms.onlineqc.dto.OnlineQcRecordDto;
import com.inadev.qms.onlineqc.dto.OnlineQcRecordRequestDto;
import com.inadev.qms.onlineqc.dto.QcFormDto;
import com.inadev.qms.onlineqc.dto.QcFormListProjection;
import com.inadev.qms.onlineqc.dto.QcFormSearchRequestDto;
import com.inadev.qms.onlineqc.dto.SearchDto;
import com.inadev.qms.onlineqc.entity.OnlineQC;
import com.inadev.qms.onlineqc.entity.OnlineQcRecord;
import com.inadev.qms.onlineqc.entity.QcForm;
import com.inadev.qms.onlineqc.enums.CommonStatus;
import com.inadev.qms.onlineqc.repository.QcFormRepository;
import com.inadev.qms.onlineqc.service.OnlineQCService;
import com.inadev.qms.onlineqc.utility.MessageResult;
import com.inadev.qms.onlineqc.utility.ResponseData;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/onlineqc")
@Slf4j
public class OnlineQCController {

	@SuppressWarnings("unused")
	@Autowired
	private OnlineQCService onlineQcService;

	@Autowired
	QcFormRepository qcFormRepository;

	@GetMapping("/isServiceUp")
	public String checkHealth() {
		return "OnlineQC-Service Server is running";
	}

	@PostMapping("/addNewSection")
	public MessageResult addNewSection(@Valid @RequestBody OnlineQCDto onlineQcDto) {
		log.info("OnlineQCController : addNewSection() ", onlineQcDto);

		boolean check = onlineQcService.checkSectionExistById(onlineQcDto.getSectionId());
		if (check) {
			return MessageResult.error("Section Id already exists please enter another section id code");
		}

		OnlineQC onlineQc = null;
		try {
			onlineQc = onlineQcService.addNewSection(onlineQcDto);

		}catch (Exception e) {
			e.printStackTrace();
		}
		if (onlineQc == null) {
			log.info("OnlineQCController : addNewSection() : failure");
			return MessageResult.error("Error in creating Section");
		} else {
			log.info("OnlineQCController : addNewSection() : success");
			return MessageResult.success("Section Created Successfully");
		}
	}

	@GetMapping("/getAllSection")
	public MessageResult getAllSection() {
		log.info("ItemController : getAllSection() ");
		List<OnlineQCDto> allSections = onlineQcService.findAllSection();

		if (allSections != null && !allSections.isEmpty()) {

			ResponseData data = new ResponseData();
			data.setData(allSections);

			// data.setFilters(prepareFilterList());
			data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
			log.info("OnlineQCController : getAllSection():success: data  found");

			return MessageResult.listDataSuccess(data);

		}
		log.info("OnlineQCController : getAllSection():DataNotFound");

		return MessageResult.noContent();

	}

	@GetMapping("/getSectionById/{id}")
	public MessageResult getSectionById(@PathVariable("id") Long id) {
		log.info("OnlineQCController : getSectionbyId() ", id);
		OnlineQCDto section = onlineQcService.findSectionById(id);

		if (section != null) {
			log.info("OnlineQCController : getSectionById():success: data  found");
			return MessageResult.dataFound(section);
		} else {
			log.info("OnlineQCController : getSectionById(): data not found");
			return MessageResult.noContent();
		}
	}

	@PutMapping("/updateSection")
	public MessageResult updateSection(@Valid @RequestBody OnlineQCDto onlineQCDto) {
		log.info("ItemController : updateItem() ", onlineQCDto.getSectionId());
		OnlineQC savedSection = null;
		String sectionId = onlineQCDto.getSectionId();
		if (sectionId == null || sectionId.isBlank()) {
			return MessageResult.error("Section Id cannot be null or blank");
		}

		boolean check = onlineQcService.checkSectionExistById(onlineQCDto.getSectionId());
		if (!check) {
			return MessageResult.error("Section Id does not Exists");
		}

		try {

			savedSection = onlineQcService.updateSection(onlineQCDto);
		} catch (Exception e) {
			log.error("OnlineQCController : updateSection() : error ", e.fillInStackTrace());
		}
 
		if (savedSection != null) {
			log.info("OnlineQCController : updateSection() :success");
			return MessageResult.success("Section updated Successfully");

		} else {
			log.info("OnlineQCController : updateSection() :failure");
			return MessageResult.error("Error in updating Section");
		}
	}

	@GetMapping("/getSection/{sectionId}/{sectionName}/{status}")
	public MessageResult getSection(@PathVariable("sectionId") String sectionId,
			@PathVariable("sectionName") String sectionName, @PathVariable("status") CommonStatus status) {
		log.info("OnlineQCController : getSection() ", sectionId, sectionName, status);
		List<OnlineQCDto> onlineQcDto = onlineQcService.searchSection(sectionId, sectionName, status);

		if (onlineQcDto != null && !onlineQcDto.isEmpty()) {

			ResponseData data = new ResponseData();
			data.setData(onlineQcDto);

			data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
			log.info("OnlineQCController : getSection():success: data  found");

			return MessageResult.listDataSuccess(data);

		}
		log.info("OnlineQCController : getSection():DataNotFound");

		return MessageResult.noContent();
	}

	@PostMapping("/getSectionbyIdNameStatus")
	public MessageResult getSectionbyIdNameStatus(@Valid @RequestBody SearchDto searchDto) {

		String sectionId = searchDto.getSectionId();
		String sectionName = searchDto.getSectionName();
		CommonStatus status = searchDto.getStatus();

		log.info("OnlineQCController : getSection() ", sectionId, sectionName, status);
		List<OnlineQCDto> onlineQcDto = onlineQcService.searchSection(sectionId, sectionName, status);

		if (onlineQcDto != null && !onlineQcDto.isEmpty()) {

			ResponseData data = new ResponseData();
			data.setData(onlineQcDto);

			data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
			log.info("OnlineQCController : getSection():success: data  found");

			return MessageResult.listDataSuccess(data);

		}
		log.info("OnlineQCController : getSection():DataNotFound");

		return MessageResult.noContent();
	}

	@PostMapping("/addNewQcForm")
	public MessageResult addNewQcForm(@Valid @RequestBody QcFormDto qcFormDto) {
		log.info("OnlineQCController : addNewQcForm() ");

		QcForm qcFormCheck = qcFormRepository.findQcFormBySectionIdAndFormName(qcFormDto.getSectionId(),
				qcFormDto.getFormName());

		if (qcFormCheck != null) {
			return MessageResult.error("This " + qcFormDto.getSectionId() + " sectionId is already exits with "
					+ qcFormDto.getFormName() + " formName.");
		}

		QcForm qcForm = null;
		try {
			qcForm = onlineQcService.addNewQCForm(qcFormDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (qcForm == null) {
			log.info("OnlineQCController : addNewQcForm() : failure");
			return MessageResult.error("Error in creating QcForm");
		} else {
			log.info("OnlineQCController : addNewQcForm() : success");
			return MessageResult.success("QcForm Created Successfully");
		}
	}

	@GetMapping("/getAllQcForm")
	public MessageResult getAllForm() {
		log.info("OnlineQCController : getAllForm() ");
		List<QcFormDto> allForms = onlineQcService.getAllForms();

		if (allForms != null && !allForms.isEmpty()) {

			ResponseData data = new ResponseData();
			data.setData(allForms);

			data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
			log.info("OnlineQCController : getAllForm():success: data  found");

			return MessageResult.listDataSuccess(data);
		}
		log.info("OnlineQCController : getAllForm():DataNotFound");

		return MessageResult.noContent();
	}

	@GetMapping("/getQcFormById/{id}")
	public MessageResult getFormById(@PathVariable("id") Long id) {
		log.info("OnlineQCController : getAllForm()", id);
		QcFormDto allForm = onlineQcService.findFormById(id);

		if (allForm != null) {

			ResponseData data = new ResponseData();
			data.setData(allForm);

			data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
			log.info("OnlineQCController : getFormById():success: data  found");

			return MessageResult.listDataSuccess(data);
		}
		log.info("OnlineQCController : getFormById():DataNotFound");

		return MessageResult.noContent();
	}

	@PutMapping("/updateQcForm")
	public MessageResult updateQcForm(@Valid @RequestBody QcFormDto qcFormDto) {
		log.info("OnlineQCController : editQcForm() ", qcFormDto.getId());
		QcForm savedQcForm = null;
		Long id = qcFormDto.getId();

		if (id == null) {
			return MessageResult.error("Id cannot be null ");
		}

		try {
			savedQcForm = onlineQcService.updateQcForm(qcFormDto);
		} catch (Exception e) {
			log.error("OnlineQCController : editQcForm() : error ", e.fillInStackTrace());
		}

		if (savedQcForm != null) {
			log.info("OnlineQCController : editQcForm() :success");
			return MessageResult.success("QcForm updated Successfully");
		} else {
			log.info("OnlineQCController : editQcForm() :failure");
			return MessageResult.error("Error in updating QcForm");
		}
	}

	@PostMapping("/searchQcForm")
	public MessageResult searchForm(@RequestBody QcFormSearchRequestDto searchForm) {

		log.info("OnlineQCController : searchForm()", searchForm);

		List<QcFormListProjection> list = onlineQcService.searchQcForm(searchForm);
		if (list == null || list.isEmpty()) {

			return MessageResult.noContent();
		}

		log.info("OnlineQCController : searchForm(): success ");
		ResponseData data = new ResponseData();
		data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
		data.setData(list);

		return MessageResult.dataFound(data);
	}

	@GetMapping("/getQcFormDetailsBySectionId/{sectionId}")
	public MessageResult getQcDetailsBySectionId(@PathVariable("sectionId") String sectionId) {
		log.info("OnlineQCController : getQcDetailsBySectionId()", sectionId);
		List<QcFormDto> allForm = onlineQcService.findQcFormDetailsBySectionId(sectionId);

		if (allForm != null) {

			ResponseData data = new ResponseData();
			data.setData(allForm);

			data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
			log.info("OnlineQCController : getQcDetailsBySectionId():success: data  found");

			return MessageResult.listDataSuccess(data);
		}
		log.info("OnlineQCController : getQcDetailsBySectionId():DataNotFound");
		return MessageResult.noContent();
	}

	@PostMapping("/addNewQcRecord")
	public MessageResult addNewQcRecord(@Valid @RequestBody OnlineQcFormStructureDto onlineQcFormStructureDto) {
	 log.info("OnlineQCController : addNewQcRecord() ");
		
		OnlineQcRecord onlineQcRecord = null;
		try {
			onlineQcRecord = onlineQcService.addNewQcRecord(onlineQcFormStructureDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (onlineQcRecord == null) {
			log.info("OnlineQCController : addNewQcRecord() : failure");
			return MessageResult.error("Error in creating QcRecord");
		} else {
			log.info("OnlineQCController : addNewQcRecord() : success");
			return MessageResult.success("QcRecord Created Successfully");
		}
	}

	@PostMapping("/searchOnlineQcRecord")
	public MessageResult searchOnlineQcRecord(@RequestBody OnlineQcRecordRequestDto onlineQcRecordRequestDto) {

		log.info("OnlineQCController : searchOnlineQcRecord()", onlineQcRecordRequestDto);
		List<OnlineQcRecordDto> list = onlineQcService.getOnlineQcRecord(onlineQcRecordRequestDto.getSectionId(),
				onlineQcRecordRequestDto.getFactoryName(), onlineQcRecordRequestDto.getUnitName(),
				onlineQcRecordRequestDto.getFormName(), onlineQcRecordRequestDto.getFromDate(),
				onlineQcRecordRequestDto.getToDate());
		if (list == null || list.isEmpty()) {
			return MessageResult.noContent();
		}
		log.info("OnlineQCController : searchOnlineQcRecord(): success ");
		ResponseData data = new ResponseData();
		data.setActions(new ArrayList<>(Arrays.asList("view", "edit")));
		data.setData(list);

		return MessageResult.dataFound(data);
	}

	@PostMapping("/isQcFormNameExists")
	public MessageResult isQcFormNameExists(@Valid @RequestBody QcFormDto qcFormDto) {
		log.info("OnlineQCController : isQcFormNameExists() ", qcFormDto);

		String sectionId = qcFormDto.getSectionId();
		String formName = qcFormDto.getFormName();
		String oemCode = qcFormDto.getOemCode();
		if (sectionId == null || sectionId.isBlank() || formName == null || formName.isBlank() || oemCode == null || oemCode.isBlank()) {
			return MessageResult.error("sectionId or formName or oemCode cannot be null or blank");
		}

		List<QcForm> qcForm = onlineQcService.checkQcFormExistByOemCodeAndSectionIdAndFormName(oemCode,sectionId, formName);
		if (qcForm == null || qcForm.isEmpty()) {
			log.info("OnlineQCController : isQcFormNameExists() :success");
			return MessageResult.success("SectionId and FormName does not exist");
		} else {
			log.info("OnlineQCController : isQcFormNameExists() :failure");
			return MessageResult.error("SectionId and FormName Exist");
		}

	}
}