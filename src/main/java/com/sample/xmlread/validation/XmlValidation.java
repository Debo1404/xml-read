package com.sample.xmlread.validation;

import org.springframework.util.StringUtils;

import com.google.common.io.Files;
import com.sample.xmlread.exception.PathValueException;

public class XmlValidation {

	public static void validate(String path) {
		if(StringUtils.isEmpty(path)) {
			throw new PathValueException("Path cannot be null or empty");
		}
		 if(!path.contains(":\\")) {
			 throw new PathValueException("Invalid file path");
		 }
		 String extension = Files.getFileExtension(path);
		 if(!"xml".equals(extension)) {
			 throw new PathValueException("File extension not correct");
		 }
		 
	}
}
