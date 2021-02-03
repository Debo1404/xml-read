package com.sample.xmlread.service;

import java.util.List;

import com.sample.xmlread.dto.IPsubnetdto;


public interface XmlService {

	/**
	 * @param path Path of the file location
	 * @return Boolean Whether successfully saved or not
	 */
	Boolean saveData(String path);

	/**
	 * @return List List of data in required format
	 */
	List<IPsubnetdto> getData();
}
