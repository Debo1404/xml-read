package com.sample.xmlread.service;

import java.util.List;

import com.sample.xmlread.dto.IPsubnetdto;

public interface XmlService {

	Boolean saveData(String path);

	List<IPsubnetdto> getData();
}
