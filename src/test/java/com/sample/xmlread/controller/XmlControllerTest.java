package com.sample.xmlread.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.dto.PathDto;
import com.sample.xmlread.exception.PathValueException;
import com.sample.xmlread.service.XmlServiceImpl;

@ExtendWith(MockitoExtension.class)
public class XmlControllerTest {

	@InjectMocks
	private XmlController xmlController;
	
	@Mock
	private XmlServiceImpl xmlService;

	
	@Test
	public void saveDataTest() {
		String path = "C:\\testfolder\testfile.xml";
		PathDto dto = new PathDto();
		dto.setPath(path);
		Mockito.when(xmlService.saveData(Mockito.anyString())).thenReturn(true);
		ResponseEntity res = xmlController.saveData(dto);
		Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
	}
	
	@Test
	public void saveDataExceptionTest() {
		String path = "C:\\testfolder\\testfile.xml";
		PathDto dto = new PathDto();
		dto.setPath(path);
		Mockito.when(xmlService.saveData(Mockito.anyString())).thenReturn(false);
		ResponseEntity res = xmlController.saveData(dto);
		Assert.assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
	}
	
	@Test
	public void saveDataExceptionv1Test() {
		String path = "C:\\testfolder\\testfile.xml";
		PathDto dto = new PathDto();
		dto.setPath(path);
		Mockito.when(xmlService.saveData(Mockito.anyString())).thenThrow(PathValueException.class);
		Exception exception = assertThrows(PathValueException.class, () -> xmlController.saveData(dto));
		//ResponseEntity res = xmlController.saveData(dto);
		//assertEquals("Could not Fetch Data", exception.getMessage());
	}
	
	@Test
	public void getDataTest() {
		List<IPsubnetdto> list = new ArrayList<>();
		IPsubnetdto dto = new IPsubnetdto();
		dto.setDocumentName("testname");
		dto.setNetworkName("test network");
		dto.setTadigCode("testcode");
		dto.setLastIpDate(new Timestamp(System.currentTimeMillis()));
		List<String> ipList = new ArrayList<>();
		ipList.add("127.0.0.1/32");
		dto.setRawip(ipList);
		list.add(dto);
		Mockito.when(xmlService.getData()).thenReturn(list);
		ResponseEntity res = xmlController.getData();
		Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
	}
	
	@Test
	public void getDataNoContentTest() {
		Mockito.when(xmlService.getData()).thenReturn(new ArrayList<>());
		ResponseEntity res = xmlController.getData();
		Assert.assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
	}
	
	@Test
	public void getDataExceptionTest() {
		Mockito.when(xmlService.getData()).thenThrow(RuntimeException.class);
		ResponseEntity res = xmlController.getData();
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
	}
	
}
