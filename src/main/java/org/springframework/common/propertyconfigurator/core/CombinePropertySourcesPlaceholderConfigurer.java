package org.springframework.common.propertyconfigurator.core;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.Assert;

public class CombinePropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

	private final Properties props = new Properties();
	
	public CombinePropertySourcesPlaceholderConfigurer(ConfigurationSource... configurationSources) {
		Assert.notNull(configurationSources, "Configuration Sources must be provided.");
		for (ConfigurationSource source : configurationSources) {
			props.putAll(source.getProperties());
		}
		
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
