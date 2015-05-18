package com.ericsson.ipm.v1.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 4798731111593973115L;

	private Integer id;
	
	private String name;
	
	private String code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
