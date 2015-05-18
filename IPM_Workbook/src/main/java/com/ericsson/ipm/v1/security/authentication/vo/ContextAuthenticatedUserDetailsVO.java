package com.ericsson.ipm.v1.security.authentication.vo;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ericsson.ipm.v1.domain.UserProfile;
import com.ericsson.ipm.v1.vo.UserPrincipalVO;
import com.ericsson.v1.util.Constants;



/**
 * @author ihkhan
 * 
 */
public class ContextAuthenticatedUserDetailsVO implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1545831155510037016L;
	private UserPrincipalVO principal;
	private String username;
	private Collection<? extends GrantedAuthority> grantedAuths;
	private UserProfile profile;
	private String password;

	public ContextAuthenticatedUserDetailsVO(String username,
			Collection<? extends GrantedAuthority> grantedAuths,
			UserProfile profile) {
		super();
		this.username = username;
		this.grantedAuths = grantedAuths;
		this.profile = profile;
	}
	
	public ContextAuthenticatedUserDetailsVO(String username, String password, 
			Collection<? extends GrantedAuthority> grantedAuths,
			UserProfile profile) {
		super();
		this.username = username;
		this.grantedAuths = grantedAuths;
		this.profile = profile;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuths;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		//return "NA";
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public UserPrincipalVO getPrincipal() {
		return principal;
	}

	public void setPrincipal(UserPrincipalVO principal) {
		this.principal = principal;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public boolean isUserInRole(Constants.APP_ROLES userRole) {
		return (grantedAuths.contains(new CustomGrantedAuthority(userRole
				.toString())));
	}
	
	
}
