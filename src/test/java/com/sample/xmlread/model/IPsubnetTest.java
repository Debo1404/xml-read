package com.sample.xmlread.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IPsubnetTest {

	@Test
	public void getterSetterTest() {
		IPsubnet s = new IPsubnet();
		s.getCreatedDate();
		s.getId();
		s.getIpV4Address();
		s.getIr21Id();
		s.getRawIp();
		s.getSubnetMask();
		s.toString();
		s.equals(new IPsubnet());
		s.hashCode();
		IPsubnet s1 = new IPsubnet();
		s1.setId(100);
		s.equals(s1);
	}
}
