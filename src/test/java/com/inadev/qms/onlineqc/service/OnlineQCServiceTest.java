package com.inadev.qms.onlineqc.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;

//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.inadev.qms.onlineqc.dto.OnlineQCDto;
import com.inadev.qms.onlineqc.entity.OnlineQC;
import com.inadev.qms.onlineqc.enums.CommonStatus;
import com.inadev.qms.onlineqc.repository.OnlineQCRepository;
import com.inadev.qms.onlineqc.repository.OnlineQcRecordDateRepository;
import com.inadev.qms.onlineqc.repository.OnlineQcRecordFieldRepository;
import com.inadev.qms.onlineqc.repository.OnlineQcRecordRepository;
import com.inadev.qms.onlineqc.response.ResponseDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.longThat;
//import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class OnlineQCServiceTest {
	
	@InjectMocks
	OnlineQCService onlineQCService;
	
	@Mock
	OnlineQCRepository onlineQCRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private OnlineQcRecordRepository onlineQcRecordRepository;

	@Mock
	private OnlineQcRecordFieldRepository onlineQcRecordFieldRepository;
	
	@Mock
	private OnlineQcRecordDateRepository onlineQcRecordDateRepository;
	
	@BeforeAll
	public void init() {

		onlineQCRepository = mock(OnlineQCRepository.class);
		onlineQCService = new OnlineQCService();
	}
	
	@Test
	@DisplayName("Save Section")
	void saveSectionTest() throws Exception
	{
		String sectionId = "1";
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		lenient().when(onlineQCRepository.save(actualOnlineQC)).thenReturn(actualOnlineQC);
		ResponseDTO<OnlineQC> expecteDto = onlineQCService.save(onlineQCDto);
		
		System.out.println(expecteDto);
	}
	
	@Test
	@DisplayName("Add New Section")
	void addNewSectionTest() {
		
		String sectionId = "1";
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		when(modelMapper.map(any(), any())).thenReturn(actualOnlineQC);
		when(onlineQCRepository.save(actualOnlineQC)).thenReturn(actualOnlineQC);
		
		OnlineQC expectQc = onlineQCService.addNewSection(onlineQCDto);
		
		assertEquals(actualOnlineQC.getSectionId(), expectQc.getSectionId());
	}
	
	@Test
	@DisplayName("Get All Section")
	void findAllSectionTest() {
		
		String sectionId = "1";
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC onlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		lenient().when(modelMapper.map(any(), any())).thenReturn(onlineQCDto);
		when(onlineQCRepository.findAll()).thenReturn(Arrays.asList(onlineQC));
		
		List<OnlineQCDto> onlineQCDtos = onlineQCService.findAllSection();
		System.out.println(onlineQCDtos);
		
		assertNotNull(onlineQCDtos);
		assertFalse(onlineQCDtos.isEmpty());
	}
	
	@Test
	@DisplayName("Get All Section: Null Exception")
	void findAllSectionNullTest() {
		
		String sectionId = null;
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(null).sectionId(null).factoryCode(null).factoryName(null).oemId(null).oemName(null).unitCode(null).unitName(null).build();
		
		lenient().when(modelMapper.map(any(), any())).thenReturn(onlineQCDto);
		lenient().when(onlineQCRepository.findAll()).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> onlineQCDtos = onlineQCService.findAllSection();
		
		System.out.println(onlineQCDtos);
	}
	
	@Test
	@DisplayName("Get All Section: Empty")
	void findAllSectionEmptyTest() {
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("").sectionId("").factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("").sectionId("").factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		lenient().when(modelMapper.map(any(), any())).thenReturn(onlineQCDto);
		lenient().when(onlineQCRepository.findAll()).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> onlineQCDtos = onlineQCService.findAllSection();
		
		System.out.println(onlineQCDtos);
	}

	@Test
	@DisplayName("Get Section By Id")
	void findSectionByIdTest() throws Exception {
		
		Long id = 1L;
		
		OnlineQC onlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
				
		when(modelMapper.map(any(), any())).thenReturn(onlineQC);
		when(onlineQCRepository.findSectionById(id)).thenReturn(onlineQC);
		
		OnlineQCDto expecteDto = onlineQCService.findSectionById(id);
		System.out.println(expecteDto);
		
		
	}
	
	@Test
	@DisplayName("Get Section By Id: Null")
	void findSectionByIdNullTest() throws Exception {
		
		long id = 1;
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").id(id).status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		lenient().when(modelMapper.map(any(), any())).thenReturn(onlineQCDto);
		lenient().when(onlineQCRepository.findSectionById(null)).thenReturn(actualOnlineQC);
		
		OnlineQCDto expecteDto = onlineQCService.findSectionById(id);
		
		System.out.println(expecteDto);
	
	}
	
	@Test
	@DisplayName("Get Section By Id: Exception")
	void findSectionByIdExceptionTest() throws Exception {
		
		long id = 1;
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").id(id).status(CommonStatus.ACTIVE).build();
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		lenient().when(modelMapper.map(any(), any())).thenReturn(onlineQCDto);
		lenient().when(onlineQCRepository.findSectionById(null)).thenReturn(actualOnlineQC);
		
		OnlineQCDto expecteDto = onlineQCService.findSectionById(id);
		
		System.out.println(expecteDto);
	
	}
	
	@Test
	@DisplayName("check Section exists by sectionId")
	void checkSectionExistByIdTest() {
		String sectionId = "1";
		lenient().when(onlineQCRepository.existsBySectionId(sectionId)).thenReturn(true);
		
		Boolean expectedId = onlineQCService.checkSectionExistById(sectionId);
	
		assertEquals(true, expectedId);
		
	}
	
	@Test
	@DisplayName("Update Section")
	void updateSectionTest() throws Exception {
		
		long id = 1;
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").id(id).status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		when(onlineQCRepository.findSectionById(id)).thenReturn(actualOnlineQC);
		when(onlineQCRepository.save(actualOnlineQC)).thenReturn(actualOnlineQC);
		
		OnlineQC expectQc = onlineQCService.updateSection(onlineQCDto);
		
		System.out.println(expectQc);
		
		assertEquals(actualOnlineQC.getId(), expectQc.getId());
		
	}
	
	@Test
	@DisplayName("Update Section: Null")
	void updateSectionNullTest() throws Exception {
		
		long id = 1;
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").id(id).status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		lenient().when(onlineQCRepository.findSectionById(id)).thenReturn(null);
		lenient().when(onlineQCRepository.save(actualOnlineQC)).thenReturn(null);
		
		OnlineQC expectQc = onlineQCService.updateSection(onlineQCDto);
		
		System.out.println(expectQc);
				
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionBySectionIdAndSectionNameAndStatusTest() throws Exception {
		
		String sectionId = "1";
		String sectionName = "Test";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		when(onlineQCRepository.searchSectionbyIdNameStatus(sectionId, sectionName, status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
		assertNotNull(expecteDtos);
		assertFalse(expecteDtos.isEmpty());
		
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionBySectionIdAndSectionNameAndStatusEmptyTest() throws Exception {
		
		String sectionId = "1";
		String sectionName = "Test";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		lenient().when(onlineQCRepository.searchSectionbyIdNameStatus(sectionId, sectionName, status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
			
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionBySectionIdAndStatusTest() throws Exception {
		
		String sectionId = "1";
		String sectionName = "";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		when(onlineQCRepository.searchSectionbyIdStatus(sectionId, status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
		assertNotNull(expecteDtos);
		assertFalse(expecteDtos.isEmpty());
		
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionBySectionIdAndStatusEmptyTest() throws Exception {
		
		String sectionId = "1";
		String sectionName = "";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		lenient().when(onlineQCRepository.searchSectionbyIdStatus(sectionId, status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionBySectionNameAndStatusTest() throws Exception {
		
		String sectionId = "";
		String sectionName = "Test";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		when(onlineQCRepository.searchSectionbyNameStatus(sectionName, status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
		assertNotNull(expecteDtos);
		assertFalse(expecteDtos.isEmpty());
		
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionBySectionNameAndStatusEmptyTest() throws Exception {
		
		String sectionId = "";
		String sectionName = "Test";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		lenient().when(onlineQCRepository.searchSectionbyNameStatus(sectionName, status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
	}
	
	
	@Test
	@DisplayName("Search Section")
	void searchSectionByStatusTest() throws Exception {
		
		String sectionId = "";
		String sectionName = "";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		when(onlineQCRepository.searchSectionbyStatus(status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
		assertNotNull(expecteDtos);
		assertFalse(expecteDtos.isEmpty());
		
	}
	
	@Test
	@DisplayName("Search Section")
	void searchSectionByStatusEmptyTest() throws Exception {
		
		String sectionId = "";
		String sectionName = "";
		CommonStatus status = CommonStatus.ACTIVE;
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").build();
		
		lenient().when(onlineQCRepository.searchSectionbyStatus(status)).thenReturn(Arrays.asList(actualOnlineQC));
		
		List<OnlineQCDto> expecteDtos = onlineQCService.searchSection(sectionId, sectionName, status);
		System.out.println(expecteDtos);
		
	}
			

}
