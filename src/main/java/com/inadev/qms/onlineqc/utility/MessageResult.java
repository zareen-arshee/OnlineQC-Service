package com.inadev.qms.onlineqc.utility;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResult {

	private int code;
	private String status;
	private String message;
	private Object data;





	public MessageResult(int code , String status){
		this.code = code;
		this.status = status;
	}
	public MessageResult(int code ,String status, String msg){
		this.code = code;
		this.status=status;
		this.message = msg;
	}



	public static MessageResult success(){
		return new MessageResult(0,"SUCCESS");
	}
	public static MessageResult success(String msg){
		return new MessageResult(0,"SUCCESS",msg);
	}
	public static MessageResult listDataSuccess(ResponseData data){
		return new MessageResult(0,"SUCCESS","",data);
	}
	
	

	public static MessageResult dataFound(Object data){
		return new MessageResult(0,"SUCCESS","",data);
	}
	public static MessageResult noContent(){
		return new MessageResult(204,"SUCCESS","No Content");
	}

	public static MessageResult error(int code,String msg){
		return new MessageResult(code,"FAIL",msg);
	}


	public static MessageResult error(String msg){
		return new MessageResult(500,"FAIL",msg);
	}

	public static MessageResult validationError(Object data){
		return new MessageResult(400,"ERROR","",data);
	}
	
	public static MessageResult uploadError(){
		return new MessageResult(500,"FAIL","Error in  uploading");
	}

	public static MessageResult uploadSuccess(String  url){
		return new MessageResult(0,"SUCCESS","File upload success",url);
	}
}
