package com.sample.xmlread.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.xmlread.dao.XmlRepository;
import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.helper.XmlHelper;
import com.sample.xmlread.model.IPsubnet;
import com.sample.xmlread.util.XmlUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class XmlServiceImpl implements XmlService{
	
	@Autowired
	XmlRepository xmlRepository;
	
	@Autowired
	XmlHelper xmlHelper;
	
//	@Autowired
//	XmlMapper xmlMapper;

	@Override
	public Boolean saveData(String path) {

		try {
			List<IPsubnet> list = XmlUtil.readXml(path);
			xmlRepository.saveAll(list);
			return true;
		} catch (Exception e) {
			log.error("Error in saving xml data");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<IPsubnetdto> getData() {

		log.info("*******In XmlServiceImpl********");
		List<IPsubnetdto> ipdtoList = null;
		try {
			List<IPsubnet> ipList = (List<IPsubnet>) xmlRepository.findAll();
			//ipdtoList = xmlMapper.toDtoList(ipList);
			ipdtoList = xmlHelper.toDtoList(ipList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error collecting data");
			throw new RuntimeException("Could not Fetch Data");
		}

		return ipdtoList;
	}

}
