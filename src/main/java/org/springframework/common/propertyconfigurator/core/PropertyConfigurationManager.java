package org.springframework.common.propertyconfigurator.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import org.springframework.common.propertyconfigurator.utils.ApplicationRuntimeException;

public class PropertyConfigurationManager {

	private static final Logger logger = LoggerFactory.getLogger(PropertyConfigurationManager.class);
	private static final String APP_CONF_DIR = System.getProperty("app.conf.dir");
	private static Properties props = new Properties();
	
	protected static void setProps(Properties props) {
		if (props != null) {
			PropertyConfigurationManager.props.putAll(props);
		}
	}
	
	private static synchronized void initialize() {
		if (props.isEmpty()) {
			try {
				loadProperties("application.properties", true);
				if (APP_CONF_DIR != null) {
					String path =  APP_CONF_DIR + "/app-environment.properties";
					loadProperties(path, false);
				}
			} catch (Exception e) {
				throw new RuntimeException("Error initializing PropertyConfigurationManager", e);
			}
		}
	}
	
	private static void loadProperties(String path, boolean classpathResource) throws IOException {
		logger.info("loading properties from : "+path);
		try {
			if (classpathResource) {
				props.load(new ClassPathResource(path).getInputStream());
			} else {
				props.load(new FileInputStream(path));
			}
		} catch (FileNotFoundException fnfe) {
			logger.error("Property Files {} not found", path);
		}
	}
	
	public static String getValue(String key) {
		return getValue(key, null);
	}
	
	public static String getValue(String key, String defaultValue) {
		if (props.isEmpty()) {
			initialize();
		}
		String value = System.getProperty(key, props.getProperty(key, defaultValue));
		if (value == null) {
			throw new ApplicationRuntimeException("Property "+key+" has not set.");
		}
		return value;
	}
}
