package com.sample.xmlread;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
class ApplicationTests {


	@InjectMocks
	private Application applicaiton;
	
	@Test
	void contextLoads() {
	}

	
	@Test
	public void shouldCreateObject() {
		Application object = new Application();

		Assert.assertNotNull(object);
	}
	
	@Test
	public void applicationContextTest() {
	    Application.main(new String[] {});
	}

}
