package com.stackroute.newz.commander.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.stackroute.newz.model.NewsSource;

class NewsSourceTest {

	private NewsSource newsSource;

	@BeforeEach
	public void setUp() throws Exception {
		
		newsSource = new NewsSource();
		
		newsSource.setSourceId(1);
		newsSource.setSourceName("NewsDaily");
		newsSource.setSourceDesc("Fresh news updated every 30 mins");
		newsSource.setSourceCreationDate(LocalDateTime.now());
		newsSource.setSourceCreatedBy("Anita");

		
	}

	@AfterEach
	public void tearDown() throws Exception {
		
		
	}

	@Test
	public void Beantest() {
		BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection().addFactory(LocalDateTime.class, new LocalDateTimeFactory());
        beanTester.testBean(NewsSource.class);


	}

}
