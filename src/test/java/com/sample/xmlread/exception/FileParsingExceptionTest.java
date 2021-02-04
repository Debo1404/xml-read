package com.sample.xmlread.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FileParsingExceptionTest {

	@InjectMocks
	FileParsingException fileParsingException;
	
	@Test
	public void fileParsingExceptionTest() {
		FileParsingException obj = new FileParsingException("test message");
	}
}
