package com.sample.xmlread.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.xmlread.dto.PathDto;
import com.sample.xmlread.exception.PathValueException;

@ExtendWith(MockitoExtension.class)
public class XmlValidationTest {

	@InjectMocks
	XmlValidation xmlValidation;
	
	@Test
	public void validateTest() {
		String path = "C:\\testfile.xml";
		PathDto dto = new PathDto();
		dto.setPath(path);
		xmlValidation.validate(dto);
	}
	
	@Test
	public void validateEmptyTest() {
		String path = "";
		PathDto dto = new PathDto();
		dto.setPath(path);
		Exception exception = assertThrows(PathValueException.class, () -> xmlValidation.validate(dto));
		assertEquals("Path cannot be null or empty", exception.getMessage());
		
	}
	
	@Test
	public void validateInvalidPathTest() {
		String path = "abcde";
		PathDto dto = new PathDto();
		dto.setPath(path);
		Exception exception = assertThrows(PathValueException.class, () -> xmlValidation.validate(dto));
		assertEquals("Invalid file path", exception.getMessage());
		
	}
	
	@Test
	public void validateInvalidExtensionTest() {
		String path = "C:\\testfile.txt";
		PathDto dto = new PathDto();
		dto.setPath(path);
		Exception exception = assertThrows(PathValueException.class, () -> xmlValidation.validate(dto));
		assertEquals("File extension not correct", exception.getMessage());
		
	}
}
