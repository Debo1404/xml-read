package com.sample.xmlread.response;

import lombok.Data;

@Data
public class XmlResponse{

	private String message;

	public XmlResponse(String message){
		this.message = message;
	}
	
	
}
