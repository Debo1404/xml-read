package com.sample.xmlread.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.xmlread.constant.Constant;
import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.dto.PathDto;
import com.sample.xmlread.response.XmlResponse;
import com.sample.xmlread.service.XmlService;
import com.sample.xmlread.validation.XmlValidation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value="XML Read", description="Operations pertaining to read xm file from given path")
public class XmlController {
	
	@Autowired
	XmlService xmlService;
	
	
	@ApiOperation(value="Read and save values", notes = "Read xml file, and save in db")
	@RequestMapping(value = "/xmldata/save", method = RequestMethod.POST,
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity saveData(@RequestBody PathDto pathDto) {
		XmlValidation.validate(pathDto);
		log.info("Request Received to read xml file from = {}", pathDto.getPath());
		Boolean isSaved = xmlService.saveData(pathDto.getPath());
		if (isSaved) {
			return ResponseEntity.ok().body(new XmlResponse(Constant.SAVED, Constant.ERROR_CODE_200));
		}

		return ResponseEntity.noContent().build();

	}
	
	
	@ApiOperation(value="get values", notes = "Display the existing data read from xml files")
	@RequestMapping(value = "/xmldata/get", method = RequestMethod.GET,
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity getData() {
		try{
			log.info("Request Received to get all data");
			List<IPsubnetdto> res =  xmlService.getData();
			if(CollectionUtils.isEmpty(res)) {
				return ResponseEntity.noContent().build();
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
