package com.inadev.qms.onlineqc.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inadev.qms.onlineqc.dto.OnlineQCDto;
import com.inadev.qms.onlineqc.entity.OnlineQC;
import com.inadev.qms.onlineqc.enums.CommonStatus;
import com.inadev.qms.onlineqc.repository.OnlineQCRepository;
import com.inadev.qms.onlineqc.repository.QcFormRepository;
import com.inadev.qms.onlineqc.response.ResponseDTO;
import com.inadev.qms.onlineqc.service.OnlineQCService;
@WebMvcTest(OnlineQCController.class)
@Tag("IntegrationTest")
@DisplayName("OnlineQc Service Integration Tests")
public class OnlineQCControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	OnlineQCService onlineQCService;
	@MockBean
	QcFormRepository qcFormRepository;	
	@MockBean
	OnlineQCRepository onlineQCRepository;	
	@Test
	@DisplayName("check health")
	void checkHealthTest() throws Throwable {
		String expected = "OnlineQC-Service Server is running";
		String jsonString = JsonUtil.objectToJson(expected);
		mockMvc.perform(get("/onlineqc/isServiceUp").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
				.andExpect(status().isOk()).andExpect(jsonPath("$", is(expected)));
	}

	
	
	
	@Test
	@DisplayName("Add New Section")
	public void addNewSection() throws Exception {
		
		String sectionId = "1";
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCRepository.existsBySectionId(actualOnlineQC.getSectionId())).thenReturn(true);		
		when(onlineQCService.checkSectionExistById(sectionId)).thenReturn(true);
		
		mockMvc.perform(post("/onlineqc/addNewSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Add New Section")
	public void addNewSectionWhenSectionDoesNotExistTest() throws Exception {
		
		String sectionId = "1";
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCRepository.existsBySectionId(actualOnlineQC.getSectionId())).thenReturn(false);		
		when(onlineQCService.checkSectionExistById(sectionId)).thenReturn(false);
		
		mockMvc.perform(post("/onlineqc/addNewSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	
	@Test
	@DisplayName("Add New Section When Section Null")
	public void addNewSectionWhenSectionNullTest() throws Exception {
		
		String sectionId = null;
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId(";").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.addNewSection(onlineQCDto)).thenReturn(actualOnlineQC);
		
		mockMvc.perform(post("/onlineqc/addNewSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("add new section : Exception")
	public void addNewSectionExceptionTest() throws Exception {
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId("1").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.addNewSection(null)).thenReturn(null);
		
		mockMvc.perform(post("/onlineqc/addNewSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
		
	@Test
	@DisplayName("Get All Section")
	public void getAllSectionTest() throws Exception {
		
		OnlineQCDto expecteDto = OnlineQCDto.builder().sectionName("Test").sectionId("2").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		List<OnlineQCDto> allSections = new ArrayList<>();
		allSections.add(expecteDto);
		
		String jsonString = JsonUtil.objectToJson(expecteDto);
		
		when(this.onlineQCService.findAllSection()).thenReturn(allSections);
		
		mockMvc.perform(get("/onlineqc/getAllSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.data.[0].sectionId", is("2")));
}
	
	
	@Test
	@DisplayName("Get All Section")
	public void getAllSectionNullTest() throws Exception {
		
		List<OnlineQCDto> onlineQCDtos = null;
				
		String jsonString = JsonUtil.objectToJson(onlineQCDtos);
		
		when(this.onlineQCService.findAllSection()).thenReturn(null);
		
		mockMvc.perform(get("/onlineqc/getAllSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get All Section")
	public void getAllSectionEmptyTest() throws Exception {
		
		OnlineQCDto expecteDto = OnlineQCDto.builder().sectionName("").sectionId("").factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").status(CommonStatus.ACTIVE).build();
		
		List<OnlineQCDto> allSections = new ArrayList<>();
		allSections.add(expecteDto);
		
		String jsonString = JsonUtil.objectToJson(expecteDto);
		
		when(this.onlineQCService.findAllSection()).thenReturn(null);
		
		mockMvc.perform(get("/onlineqc/getAllSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get Section By Id")
	public void getSectionByIdTest() throws Exception {
		
		Long id = 1L;
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId("2").factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").id(id).status(CommonStatus.ACTIVE).build();
		
		String jsonString = JsonUtil.objectToJson(onlineQCDto);
		
		when(onlineQCService.findSectionById(id)).thenReturn(onlineQCDto);
		
		mockMvc.perform(get("/onlineqc//getSectionById/{id}",id).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andExpect(status().isOk());
		}
	
	@Test
	@DisplayName("Get Section By Id")
	public void getSectionByIdNullTest() throws Exception {
		
		Long id = 1L;
		OnlineQCDto onlineQCDto = null;
		
		String jsonString = JsonUtil.objectToJson(onlineQCDto);
		
		when(onlineQCService.findSectionById(id)).thenReturn(onlineQCDto);
		
		mockMvc.perform(get("/onlineqc//getSectionById/{id}",id).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString));
		}	
	
	@Test
	@DisplayName("Update Section")
	public void updateSectionTest() throws Exception 
	{
		
		String sectionId = "2";
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.checkSectionExistById(onlineQCDto.getSectionId())).thenReturn(true);
		when(onlineQCService.updateSection(onlineQCDto)).thenReturn(actualOnlineQC);
		
		mockMvc.perform(put("/onlineqc/updateSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Update Section")
	public void updateSectionCheckSectionIdTest() throws Exception 
	{
		
		String sectionId = "2";
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.checkSectionExistById(onlineQCDto.getSectionId())).thenReturn(false);
		when(onlineQCService.updateSection(onlineQCDto)).thenReturn(actualOnlineQC);
		
		mockMvc.perform(put("/onlineqc/updateSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Update Section")
	public void updateSectionCheckSectionIdNullTest() throws Exception 
	{
		
		String sectionId = "2";
		
		ArrayList<OnlineQC> onlineQcMappingList = new ArrayList<>();
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		actualOnlineQC.setStatus(CommonStatus.ACTIVE);
		onlineQcMappingList.add(actualOnlineQC);
		
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.checkSectionExistById(sectionId)).thenReturn(false);
		lenient().when(onlineQCService.updateSection(onlineQCDto)).thenReturn(actualOnlineQC);
		
		mockMvc.perform(put("/onlineqc/updateSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	
	@Test
	@DisplayName("Update Section")
	public void updateSectionWhenSectionNullTest() throws Exception 
	{
		
		String sectionId = "2";
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		OnlineQC onlineQC = null;
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.checkSectionExistById(onlineQCDto.getSectionId())).thenReturn(true);
		when(onlineQCService.updateSection(onlineQCDto)).thenReturn(onlineQC);
		
		mockMvc.perform(put("/onlineqc/updateSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Update Section")
	public void updateSectionWhenSectionIdDoesNotExistTest() throws Exception {
	
		String sectionId = "2";
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.checkSectionExistById(sectionId)).thenReturn(false);
		when(onlineQCService.updateSection(onlineQCDto)).thenReturn(actualOnlineQC);
		
		mockMvc.perform(put("/onlineqc/updateSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	
	}
	
	@Test
	@DisplayName("Update Section")
	public void updateSectionWhenSectionIdNullTest() throws Exception {
	
		String sectionId = null;
		OnlineQCDto onlineQCDto = OnlineQCDto.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(CommonStatus.ACTIVE).build();
		
		OnlineQC actualOnlineQC = OnlineQC.builder().sectionName("Test").sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").build();
		
		String inputInJson = objectMapper.writeValueAsString(onlineQCDto);
		
		when(onlineQCService.updateSection(onlineQCDto)).thenReturn(actualOnlineQC);
		
		mockMvc.perform(put("/onlineqc/updateSection").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)).andExpect(status().isOk());
	
	}	
	
	@Test
	@DisplayName("Get Section")
	public void getSectionTest() throws Exception 
	{
		String sectionId = "1";
		String sectionName = " Test";
		CommonStatus status = CommonStatus.ACTIVE;
				
		OnlineQCDto expecteDto = OnlineQCDto.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("d567").factoryName("ABC").oemId("687").oemName("ghj").unitCode("798").unitName("hgkjhkj").status(status).build();
		
		List<OnlineQCDto> allSections = new ArrayList<>();
		allSections.add(expecteDto);
		
		String jsonString = JsonUtil.objectToJson(expecteDto);
		
		when(this.onlineQCService.searchSection(sectionId, sectionName, status)).thenReturn(allSections);
		
		mockMvc.perform(get("/onlineqc/getSection/{sectionId}/{sectionName}/{status}",sectionId,sectionName,status).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString));
	}
	
	@Test
	@DisplayName("Get Section")
	public void getSectionNullTest() throws Exception 
	{
		String sectionId = "1";
		String sectionName = " Test";
		CommonStatus status = CommonStatus.ACTIVE;
				
		OnlineQCDto expecteDto = null;
		
		List<OnlineQCDto> allSections = new ArrayList<>();
		allSections.add(expecteDto);
		
		String jsonString = JsonUtil.objectToJson(expecteDto);
		
		when(this.onlineQCService.searchSection(sectionId, sectionName, status)).thenReturn(allSections);
		
		mockMvc.perform(get("/onlineqc/getSection/{sectionId}/{sectionName}/{status}",sectionId,sectionName,status).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString));
	}
	
	@Test
	@DisplayName("Get Section")
	public void getSectionEmptyTest() throws Exception 
	{
		String sectionId = "";
		String sectionName = " ";
		CommonStatus status = CommonStatus.ACTIVE;
				
		OnlineQCDto expecteDto = OnlineQCDto.builder().sectionName(sectionName).sectionId(sectionId).factoryCode("").factoryName("").oemId("").oemName("").unitCode("").unitName("").status(status).build();
		
		
		List<OnlineQCDto> allSections = new ArrayList<>();
		allSections.add(expecteDto);
		
		String jsonString = JsonUtil.objectToJson(expecteDto);
		
		when(this.onlineQCService.searchSection(sectionId, sectionName, status)).thenReturn(allSections);
		
		mockMvc.perform(get("/onlineqc/getSection/{sectionId}/{sectionName}/{status}",sectionId,sectionName,status).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString));
	}

}

