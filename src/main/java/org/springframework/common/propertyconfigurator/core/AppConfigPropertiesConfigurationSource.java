package org.springframework.common.propertyconfigurator.core;

/**
 * AppConfigPropertiesConfigurationSource is responsible for loading properties from classpath.
 * Example - there may be properties file named as application.properties located inside
 * src
 *  |
 *  |- main/java/all source files.
 *  |- main/resources/application.properties
 *  |- test/java/all test classes.
 *  |- test/resources/unit test application properties files.  
 * 
 */
public class AppConfigPropertiesConfigurationSource extends PropertiesFileBasedConfigurationSource {

	protected AppConfigPropertiesConfigurationSource(String path) {
		super(path, false, true);
	}
}
