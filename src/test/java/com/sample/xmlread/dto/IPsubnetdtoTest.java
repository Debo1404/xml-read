package com.sample.xmlread.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IPsubnetdtoTest {

	@Test
	public void getterSetterTest() {
		IPsubnetdto obj = new IPsubnetdto();
		obj.getDocumentName();
		obj.getNetworkName();
		obj.getLastIpDate();
		obj.getRawip();
		obj.getTadigCode();
		obj.toString();
		obj.hashCode();
		obj.equals(new IPsubnetdto());
		IPsubnetdto obj1 = new IPsubnetdto();
		obj1.setNetworkName("new name");
		obj.equals(obj1);
		
	}
}
