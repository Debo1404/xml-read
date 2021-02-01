package com.sample.xmlread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.xmlread.service.XmlService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class XmlController {
	
	@Autowired
	XmlService xmlService;
	
	@RequestMapping(value = "/xmldata/save", method = RequestMethod.POST,
			produces = "application/json")
	public ResponseEntity saveData(@RequestParam String path) {
		if(path == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Path cannot be null");
		}
		try{
			log.info("Request Received to read xml file from = {}", path);
			Boolean isSaved =  xmlService.saveData(path);
			if(isSaved) {
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}catch(Exception e){
			//handleExceptions(e);
		}
		return ResponseEntity.noContent().build();
	}
}
