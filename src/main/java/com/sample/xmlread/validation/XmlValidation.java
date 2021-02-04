package com.sample.xmlread.validation;

import org.springframework.util.StringUtils;

import com.google.common.io.Files;
import com.sample.xmlread.constant.Constant;
import com.sample.xmlread.dto.PathDto;
import com.sample.xmlread.exception.PathValueException;

public class XmlValidation {

	public static void validate(PathDto pathDto) {
		if(pathDto == null) {
			throw new PathValueException(Constant.NO_PARAMETER);
		}
		if(StringUtils.isEmpty(pathDto.getPath())) {
			throw new PathValueException(Constant.EMPTY_PATH);
		}
		 if(!pathDto.getPath().contains(Constant.PATH_FORMAT)) {
			 throw new PathValueException(Constant.INVALID_PATH);
		 }
		 String extension = Files.getFileExtension(pathDto.getPath());
		 if(!Constant.EXTN.equals(extension)) {
			 throw new PathValueException(Constant.WRONG_EXTN);
		 }
		 
	}
}
