package com.sample.xmlread.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class XmlResponseTest {

	@Test
	public void getterSetterTest() {
		XmlResponse obj = new XmlResponse("new object");
		obj.getMessage();
		obj.toString();
		obj.hashCode();
		obj.equals(new XmlResponse("new object"));
		XmlResponse obj1 = new XmlResponse("different object");
		obj.equals(obj1);
		
	}
}
