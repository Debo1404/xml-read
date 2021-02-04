package com.sample.xmlread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sample.xmlread.response.XmlResponse;

@RestControllerAdvice
public class PathValueExceptionHandler {

	@ExceptionHandler(PathValueException.class)
    public ResponseEntity<XmlResponse> toResponse(PathValueException ex)
    {
		XmlResponse errorMessage=new XmlResponse(ex.getMessage());

        return new ResponseEntity<XmlResponse>(errorMessage,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(FileParsingException.class)
    public ResponseEntity<XmlResponse> toResponse(FileParsingException ex)
    {
		XmlResponse errorMessage=new XmlResponse(ex.getMessage());

        return new ResponseEntity<XmlResponse>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
