package com.sample.xmlread.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class XmlResponseTest {

	@Test
	public void getterSetterTest() {
		XmlResponse obj = new XmlResponse("new object", 200);
		obj.getMessage();
		obj.toString();
		obj.hashCode();
		obj.equals(new XmlResponse("new object", 200));
		XmlResponse obj1 = new XmlResponse("different object", 200);
		obj.equals(obj1);		
	}
}
