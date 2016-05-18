package org.springframework.common.propertyconfigurator.core;

public class FileSystemConfigurationSource extends PropertiesFileBasedConfigurationSource {

	protected FileSystemConfigurationSource(String path) {
		super(path, true, false);
	}

}
