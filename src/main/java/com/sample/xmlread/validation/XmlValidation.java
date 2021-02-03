package com.sample.xmlread.validation;

import org.springframework.util.StringUtils;

import com.google.common.io.Files;
import com.sample.xmlread.constant.Constant;
import com.sample.xmlread.exception.PathValueException;

public class XmlValidation {

	public static void validate(String path) {
		if(StringUtils.isEmpty(path)) {
			throw new PathValueException(Constant.EMPTY_PATH);
		}
		 if(!path.contains(Constant.PATH_FORMAT)) {
			 throw new PathValueException(Constant.INVALID_PATH);
		 }
		 String extension = Files.getFileExtension(path);
		 if(!Constant.EXTN.equals(extension)) {
			 throw new PathValueException(Constant.WRONG_EXTN);
		 }
		 
	}
}
