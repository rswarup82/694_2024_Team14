package org.springframework.common.propertyconfigurator.core;

public class ClassPathConfigurationSource extends PropertiesFileBasedConfigurationSource {

	protected ClassPathConfigurationSource(String path) {
		super(path, false, true);
	}

}
