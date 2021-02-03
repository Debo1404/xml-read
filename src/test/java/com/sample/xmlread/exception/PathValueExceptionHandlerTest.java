package com.sample.xmlread.exception;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.sample.xmlread.response.XmlResponse;

@ExtendWith(MockitoExtension.class)
public class PathValueExceptionHandlerTest {

	@InjectMocks
	PathValueExceptionHandler pathValueExceptionHandler;
	
	@Test
	public void pathValueExceptionHandlerTest() {
		PathValueException p = new PathValueException("test message");
		ResponseEntity<XmlResponse> res = pathValueExceptionHandler.toResponse(p);
		Assert.assertEquals("test message", res.getBody().getMessage());
	}
}
