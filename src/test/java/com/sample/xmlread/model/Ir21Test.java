package com.sample.xmlread.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Ir21Test {

	@Test
	public void getterSetterTest() {
		Ir21 obj = new Ir21();
		obj.getDocumentName();
		obj.getId();
		obj.getNetworkName();
		obj.getTadIgCode();
		obj.toString();
		obj.hashCode();
		obj.equals(new Ir21());
		Ir21 obj1 = new Ir21();
		obj1.setId(100);
		obj.equals(obj1);
		
	}
}
