package com.sample.xmlread.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PathDtoTest {

	@Test
	public void getterSetterTest() {
		PathDto obj = new PathDto();
		obj.getPath();
		obj.toString();
		obj.hashCode();
		obj.equals(new PathDto());
		PathDto obj1 = new PathDto();
		obj1.setPath("new paath");
		obj.equals(obj1);
	}
}
