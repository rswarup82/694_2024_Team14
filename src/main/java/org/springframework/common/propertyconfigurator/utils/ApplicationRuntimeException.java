package org.springframework.common.propertyconfigurator.utils;

public class ApplicationRuntimeException extends RuntimeException {

	private boolean logged;
	
	public ApplicationRuntimeException() {
		super();
	}
	
	public ApplicationRuntimeException(String msg) {
		super(msg);
	}
	
	public ApplicationRuntimeException(String msg, Throwable t) {
		super(msg, t);
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}	
}
