/**
 * 
 */
package com.ericsson.ipm.v1.security.authentication.vo;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author ihkhan
 * 
 */
public class CustomGrantedAuthority implements GrantedAuthority, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6221790702259124511L;
	private String authority;

	public CustomGrantedAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomGrantedAuthority other = (CustomGrantedAuthority) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		return true;
	}

}
