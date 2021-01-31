package com.sample.xmlread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.xmlread.service.XmlService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class XmlController {
	
	@Autowired
	XmlService xmlService;
	
	@RequestMapping("/xmldata/save/{path}")
	public ResponseEntity saveData(@PathVariable String path) {
		if(path == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Path cannot be null");
		}
		try{
			log.info("Request Received to read xml file from = {}", path);
			//brand = categoryService.getBrandsByCategory(categoryId);
		}catch(Exception e){
			//handleExceptions(e);
		}
		return ResponseEntity.noContent().build();
	}
}
