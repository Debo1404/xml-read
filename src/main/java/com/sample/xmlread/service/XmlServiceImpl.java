package com.sample.xmlread.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.xmlread.dao.XmlRepository;
import com.sample.xmlread.model.IPsubnet;
import com.sample.xmlread.util.XmlUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class XmlServiceImpl implements XmlService{
	
	@Autowired
	XmlRepository xmlRepository;

	@Override
	public Boolean saveData(String path) {
		
		List<IPsubnet> list = XmlUtil.readXml(path);
		try {
			List<IPsubnet> savedData=(List<IPsubnet>) xmlRepository.saveAll(list);
			System.out.println("savedData"+savedData);
			return true;
		}catch(Exception e) {
			log.error("Error in saving xml data");
			e.printStackTrace();
			return false;
		}
	}

}
