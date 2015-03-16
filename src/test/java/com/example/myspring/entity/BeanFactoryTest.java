package com.example.myspring.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class BeanFactoryTest {
	
	@Test
	public void loadCarBeanTest(){
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource("classpath:applicationContext.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		Car car = beanFactory.getBean("car",Car.class);
		Assert.assertEquals(car.getColor(), "black");
	}
}
