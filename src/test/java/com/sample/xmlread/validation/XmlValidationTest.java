package com.sample.xmlread.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.xmlread.exception.PathValueException;

@ExtendWith(MockitoExtension.class)
public class XmlValidationTest {

	@InjectMocks
	XmlValidation xmlValidation;
	
	@Test
	public void validateTest() {
		String path = "C:\\testfile.xml";
		xmlValidation.validate(path);
	}
	
	@Test
	public void validateEmptyTest() {
		String path = "";
		Exception exception = assertThrows(PathValueException.class, () -> xmlValidation.validate(path));
		assertEquals("Path cannot be null or empty", exception.getMessage());
		
	}
	
	@Test
	public void validateInvalidPathTest() {
		String path = "abcde";
		Exception exception = assertThrows(PathValueException.class, () -> xmlValidation.validate(path));
		assertEquals("Invalid file path", exception.getMessage());
		
	}
	
	@Test
	public void validateInvalidExtensionTest() {
		String path = "C:\\testfile.txt";
		Exception exception = assertThrows(PathValueException.class, () -> xmlValidation.validate(path));
		assertEquals("File extension not correct", exception.getMessage());
		
	}
}
