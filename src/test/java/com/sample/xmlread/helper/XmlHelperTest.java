package com.sample.xmlread.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.model.IPsubnet;
import com.sample.xmlread.model.Ir21;

@ExtendWith(MockitoExtension.class)
public class XmlHelperTest {
	
	@InjectMocks
	XmlHelper xmlHelper;

	@Test
	public void toDtoListTest() {
		List<IPsubnet> list = new ArrayList<>();
		IPsubnet subnet = getSubnetObject();
		list.add(subnet);
		List<IPsubnetdto> dtolist = xmlHelper.toDtoList(list);
		Assert.assertNotNull(dtolist);
	}
	
	@Test
	public void toDtoMultipleTest() {
		List<IPsubnet> list = new ArrayList<>();
		IPsubnet subnet = getSubnetObject();
		IPsubnet subnet1 = getSubnetObject();
		list.add(subnet);
		list.add(subnet1);
		List<IPsubnetdto> dtolist = xmlHelper.toDtoList(list);
		Assert.assertNotNull(dtolist);
	}
	
	@Test
	public void toDtoDiffMultipleTest() {
		List<IPsubnet> list = new ArrayList<>();
		IPsubnet subnet = getSubnetObject();
		IPsubnet subnet1 = getSubnetObject();
		subnet1.getIr21Id().setDocumentName("test new doc");
		list.add(subnet);
		list.add(subnet1);
		List<IPsubnetdto> dtolist = xmlHelper.toDtoList(list);
		Assert.assertNotNull(dtolist);
	}
	
	@Test
	public void toDtoDiffMultipleTimeTest() {
		List<IPsubnet> list = new ArrayList<>();
		IPsubnet subnet = getSubnetObject();
		IPsubnet subnet1 = getSubnetObject();
		subnet1.getIr21Id().setDocumentName("test new doc");
		subnet1.setCreatedDate(new Timestamp(System.currentTimeMillis()+100L));
		list.add(subnet);
		list.add(subnet1);
		List<IPsubnetdto> dtolist = xmlHelper.toDtoList(list);
		Assert.assertNotNull(dtolist);
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
