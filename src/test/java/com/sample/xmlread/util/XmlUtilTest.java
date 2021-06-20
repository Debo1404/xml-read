package com.sample.xmlread.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.xmlread.exception.FileParsingException;
import com.sample.xmlread.model.IPsubnet;

@ExtendWith(MockitoExtension.class)
public class XmlUtilTest {

	@InjectMocks
	XmlUtil xmlUtil;	
	
	@Test
	public void readXmlTest() {
		String path = "src/test/resources/sampltestfile_one.xml";
		List<IPsubnet> list = xmlUtil.readXml(path);
		assertEquals("XYZ", list.get(0).getIr21Id().getNetworkName());
		
	}
	
	@Test
	public void readXmlDuplicateTest() {
		String path = "src/test/resources/sampltestfile_two.xml";
		List<IPsubnet> list = xmlUtil.readXml(path);
		assertEquals("", list.get(0).getIr21Id().getNetworkName());
		
	}
	
	@Test
	public void readXmlBlankValueTest() {
		String path = "src/test/resources/sampltestfile_three.xml";
		List<IPsubnet> list = xmlUtil.readXml(path);
		assertEquals("", list.get(0).getIr21Id().getTadIgCode());
		
	}
	
	

	
	@Test
	public void readXmlNoFileTest() {
		String path = "src/test/resources/test5.xml";
		Exception exception = assertThrows(FileParsingException.class, () ->xmlUtil.readXml(path));
		assertEquals(true, exception.getMessage().contains("(The system cannot find the file specified)"));
		
	}
	

}
