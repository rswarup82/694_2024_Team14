package org.springframework.common.propertyconfigurator.core;

import org.springframework.beans.factory.annotation.Value;

public class MyPropertyBean {

	@Value("#{'${property1}'}")
	private String property1;
	@Value("#{'${property2}'}")
	private String property2;
	
	public String getProperty1() {
		return property1;
	}
	public void setProperty1(String property1) {
		this.property1 = property1;
	}
	public String getProperty2() {
		return property2;
	}
	public void setProperty2(String property2) {
		this.property2 = property2;
	}	
}
