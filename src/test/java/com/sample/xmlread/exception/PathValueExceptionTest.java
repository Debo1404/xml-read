package com.sample.xmlread.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PathValueExceptionTest {

	@InjectMocks
	PathValueException pathValueException;
	
	@Test
	public void pathValueExceptionTest() {
		PathValueException p = new PathValueException("test message");
	}
}
