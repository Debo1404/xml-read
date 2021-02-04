package com.sample.xmlread.response;

import lombok.Data;

@Data
public class XmlResponse{

	private String message;
	private int statusCode;

	public XmlResponse(String message, int statusCode){
		this.message = message;
		this.statusCode = statusCode;
	}
	
	
}
