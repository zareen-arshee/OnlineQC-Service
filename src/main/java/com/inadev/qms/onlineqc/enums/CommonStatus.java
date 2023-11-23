package com.inadev.qms.onlineqc.enums;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public enum CommonStatus {
	
	ACTIVE("ACTIVE")
	,INACTIVE("INACTIVE");

	
	
private String code;
    
    private CommonStatus(String code) {
        this.code=code;
    }
    
    @JsonCreator
    public static CommonStatus decode(final String code) {
        return Stream.of(CommonStatus.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }
    
    @JsonValue
    public String getCode() {
        return code;
    }

    
    @Setter
    private String typeName;



}

