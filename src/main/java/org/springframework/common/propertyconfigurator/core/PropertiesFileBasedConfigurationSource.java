package org.springframework.common.propertyconfigurator.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public abstract class PropertiesFileBasedConfigurationSource implements ConfigurationSource {
	
	private final Logger logger = LoggerFactory.getLogger(PropertiesFileBasedConfigurationSource.class);
	
	private Properties applicationProperties = new Properties();
	
	protected PropertiesFileBasedConfigurationSource(String path, boolean isFileSystemDirectory, boolean isClasspathResource) {
		loadProperties(path, isFileSystemDirectory, isClasspathResource);
	}
	
	public Properties getProperties() {
		return applicationProperties;
	}

	private void loadProperties(String path, boolean isFileSystemDirectory, boolean isClasspathResource) {
		
		try {
			if (isFileSystemDirectory) {
				String fsConfDir = System.getProperty("app.conf.dir");
				if (fsConfDir != null) {
					path = fsConfDir + "/" + path;
				}
				FileInputStream fis = new FileInputStream(path);
				applicationProperties.load(fis);
			}
			logger.info("Loading properties from : "+path);
			
			if (isClasspathResource) {
				ClassPathResource resource = new ClassPathResource(path);
				applicationProperties.load(resource.getInputStream());
			} 
		} catch (FileNotFoundException fnfe) {
			
		} catch (IOException ioe) {
			throw new RuntimeException("Error loading properties from '" + path + "'", ioe);
		}
	}
	
	public String toString() {
		return getProperties().toString();
	}
}
