package com.ericsson.ipm.v1.vo;

import java.io.Serializable;
import java.util.Map;

public class UserPrincipalVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -887147985605584517L;
	private String id;
	private Map<String, Object> attributes;

	public UserPrincipalVO() {
		super();
	}

	public UserPrincipalVO(String id, Map<String, Object> attributes) {
		this();
		this.id = id;
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
