package com.sample.xmlread.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.response.XmlResponse;
import com.sample.xmlread.service.XmlService;
import com.sample.xmlread.validation.XmlValidation;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class XmlController {
	
	@Autowired
	XmlService xmlService;
	
	
	@RequestMapping(value = "/xmldata/save", method = RequestMethod.POST,
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity saveData(@RequestParam(required = true) String path) {
		XmlValidation.validate(path);
		try{
			log.info("Request Received to read xml file from = {}", path);
			Boolean isSaved =  xmlService.saveData(path);
			if(isSaved) {
				return ResponseEntity.ok().body(new XmlResponse("Saved Successfully"));
			}
			return new ResponseEntity<>(new XmlResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e){
			return new ResponseEntity<>(new XmlResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
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
