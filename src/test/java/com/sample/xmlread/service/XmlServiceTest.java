package com.sample.xmlread.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.xmlread.dao.XmlRepository;
import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.exception.FileParsingException;
import com.sample.xmlread.helper.XmlHelper;
import com.sample.xmlread.model.IPsubnet;
import com.sample.xmlread.model.Ir21;
import com.sample.xmlread.util.XmlUtil;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class XmlServiceTest {

	@InjectMocks
	XmlServiceImpl xmlService;

	@Mock
	XmlRepository xmlRepository;

	@Mock
	XmlHelper xmlHelper;
	
	
	@BeforeAll
	  public void init() {
	    Mockito.mockStatic(XmlUtil.class);
	  }


	@Test
	public void saveDataTest() {
		String path = "C:\\testfolder\\testfile.xml";
		//Mockito.mockStatic(XmlUtil.class);
		List<IPsubnet> subnetlist = new ArrayList<>();
		subnetlist.add(getSubnetObject());
		Mockito.when(XmlUtil.readXml(Mockito.anyString())).thenReturn(subnetlist);
		Mockito.when(xmlRepository.saveAll(Mockito.anyList())).thenReturn(new ArrayList<>());
		boolean res = xmlService.saveData(path);
		Assert.assertEquals(true, res);
	}

	@Test
	public void saveDataNoDataTest() {
		String path = "C:\\testfolder\\testfile.xml";
		//Mockito.mockStatic(XmlUtil.class);
		Mockito.when(XmlUtil.readXml(Mockito.anyString())).thenReturn(new ArrayList<>());
		Exception exception = assertThrows(FileParsingException.class, () -> xmlService.saveData(path));
		Assert.assertEquals("No data found in the file", exception.getMessage());
	}

	@Test
	public void saveDataFailTest() {
		String path = "C:\\testfolder\\testfile.xml";
		//Mockito.mockStatic(XmlUtil.class);
		List<IPsubnet> subnetlist = new ArrayList<>();
		subnetlist.add(getSubnetObject());
		Mockito.when(XmlUtil.readXml(Mockito.anyString())).thenReturn(subnetlist);
		Mockito.when(xmlRepository.saveAll(Mockito.anyList())).thenThrow(RuntimeException.class);
		Exception exception = assertThrows(RuntimeException.class, () -> xmlService.saveData(path));
		Assert.assertNotNull(exception);
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
	
	private IPsubnet getSubnetObject() {
		IPsubnet subnet = new IPsubnet();
		Ir21 ir21 = new Ir21();
		ir21.setDocumentName("test doc");
		ir21.setId(1);
		ir21.setNetworkName("testnetwork");
		ir21.setTadIgCode("test code");
		subnet.setIr21Id(ir21);
		subnet.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		subnet.setId(1);
		subnet.setIpV4Address("127.0.0.1/32");
		subnet.setRawIp("127.0.0.1");
		subnet.setSubnetMask("255.255.255.255");
		return subnet;
	}
}
