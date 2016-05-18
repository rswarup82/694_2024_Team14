package org.springframework.common.propertyconfigurator.core;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.Assert;

public class CombinePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private final Properties props = new Properties();
	
	public CombinePropertyPlaceholderConfigurer(ConfigurationSource... configurationSources) {
		Assert.notNull(configurationSources, "Configuration Sources must be provided.");
		for (ConfigurationSource source : configurationSources) {
			props.putAll(source.getProperties());
		}
		
		setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_NEVER);
		setProperties(props);
	}
	
	public void loadProperties(Properties props) throws IOException {
		props.putAll(props);
		PropertyConfigurationManager.setProps(props);
	}
	
	public void afterPropertiesSet() throws Exception {
		
	}
	
	public Properties getCombineProps() {
		return props;
	}
}
