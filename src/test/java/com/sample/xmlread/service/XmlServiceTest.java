package com.sample.xmlread.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.xmlread.dao.XmlRepository;
import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.helper.XmlHelper;
import com.sample.xmlread.model.IPsubnet;
import static org.junit.jupiter.api.Assertions.*;

/*@RunWith(PowerMockRunner.class)
@PrepareForTest(XmlUtil.class)*/
@ExtendWith(MockitoExtension.class)
public class XmlServiceTest {

	@InjectMocks
	XmlServiceImpl xmlService;

	@Mock
	XmlRepository xmlRepository;

	@Mock
	XmlHelper xmlHelper;;

//	@Test
//	public void saveDataTest() {
//		String path = "C:\\testfolder\\testfile.xml";
//		PowerMockito.mockStatic(XmlUtil.class);
//		PowerMockito.when(XmlUtil.readXml(Mockito.anyString())).thenReturn(new ArrayList<>());
//		Mockito.when(xmlRepository.saveAll(Mockito.anyList())).thenReturn(new ArrayList<>());
//		boolean res = xmlService.saveData(path);
//		Assert.assertEquals(true, res);
//	}
	
	
	@Test
	public void saveDataFailTest() {
		String path = "C:\\testfolder\\testfile.xml";
		/*
		 * PowerMockito.mockStatic(XmlUtil.class);
		 * Mockito.when(XmlUtil.readXml(Mockito.anyString())).thenReturn(new
		 * ArrayList<>());
		 */
		Exception exception = assertThrows(RuntimeException.class, () -> xmlService.saveData(path));
		Assert.assertEquals("C:\\testfolder\\testfile.xml (The system cannot find the path specified)", exception.getMessage());
	}
	
	@Test
	public void getDataTest() {
		Mockito.when(xmlRepository.findAll()).thenReturn(new ArrayList<IPsubnet>());
		Mockito.when(xmlHelper.toDtoList(Mockito.anyList())).thenReturn(new ArrayList<IPsubnetdto>());
		List<IPsubnetdto> ipdtoList = xmlService.getData();
		Assert.assertNotNull(ipdtoList);
		
	}
	
	@Test()
	public void getDataExceptionTest() {
		Mockito.when(xmlRepository.findAll()).thenReturn(new ArrayList<IPsubnet>());
		Mockito.when(xmlHelper.toDtoList(Mockito.anyList())).thenThrow(RuntimeException.class);
		Exception exception = assertThrows(RuntimeException.class, () -> xmlService.getData());
		assertEquals("Could not Fetch Data", exception.getMessage());
	}
}
