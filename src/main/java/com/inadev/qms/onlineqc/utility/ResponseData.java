package com.inadev.qms.onlineqc.utility;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ResponseData {

	
	
	Object data;
	List<String> actions;
	List<Map<String,String>> filters;
}
