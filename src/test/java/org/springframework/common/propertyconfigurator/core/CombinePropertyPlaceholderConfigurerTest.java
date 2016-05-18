package org.springframework.common.propertyconfigurator.core;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:META-INF/spring/property-placeholder-configurer-test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CombinePropertyPlaceholderConfigurerTest {

	private static Properties startProperties = new Properties();;
	private static Properties endProperties = new Properties();;
	
	@Autowired
	private MyConfigurationBean myConfigurationBean; 
	
	@Autowired
	private MyPropertyBean myPropertyBean;
	
	@Autowired
	private MyPropertyBean2 myPropertyBean2;
	
	@Before
	public void setup() {
		startProperties.setProperty("testKey", "1");
		//System.getProperties().remove("testKey");
	}
	
	@Test
	public void testPropertyConfig() {
		assertEquals("abc", myConfigurationBean.getValues()[0]);
		assertEquals("xyz", myConfigurationBean.getValues()[1]);
		assertEquals("1", myConfigurationBean.getValues()[2]);
	}
	
	@Test
	public void testThatOrderMatters_NotSystemPropertiesOverride() {
		assertEquals("1", myConfigurationBean.getValues()[2]);
		
		endProperties.setProperty("testKey", "2");
		assertEquals("1", myConfigurationBean.getValues()[2]);
	}
	
	@Test
	public void testFromJavaConfiguration() {
		assertNotNull(myPropertyBean);
		assertNotNull(myPropertyBean.getProperty1());
		assertNotNull(myPropertyBean.getProperty2());
		assertEquals("value1", myPropertyBean.getProperty1());
		assertEquals("value2", myPropertyBean.getProperty2());
	}
	
	@Test
	public void testFromXmlConfiguration() {
		assertNotNull(myPropertyBean2);
		assertNotNull(myPropertyBean2.getProperty3());
		assertNotNull(myPropertyBean2.getProperty4());
		assertEquals("value3", myPropertyBean2.getProperty3());
		assertEquals("value4", myPropertyBean2.getProperty4());
	}
	
	public static class TestConfigurationSourceStart implements ConfigurationSource {
		
		public Properties getProperties() {
			return startProperties;
		}
	
	}
	
	public static class TestConfigurationSourceEnd implements ConfigurationSource {
		
		public Properties getProperties() {
			return endProperties;
		}
	
	}
}
