package org.springframework.common.propertyconfigurator.core;

import java.util.Properties;

public class SystemPropertiesConfigurationSource implements ConfigurationSource {

	public Properties getProperties() {
		return System.getProperties();
	}

	public String toString() {
		return getProperties().toString();
	}
}
