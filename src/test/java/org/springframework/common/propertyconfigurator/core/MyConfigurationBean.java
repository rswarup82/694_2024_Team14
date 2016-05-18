package org.springframework.common.propertyconfigurator.core;

public class MyConfigurationBean {

	private String[] values;
	
	public MyConfigurationBean (String... values) {
		this.values = values;
	}
	
	public String[] getValues() {
		return this.values;
	}
}
