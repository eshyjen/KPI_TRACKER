/**
 * 
 */
package com.ericsson.ipm.v1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ihkhan
 * 
 */
@Entity
public class UserRoleAssignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026879770265467724L;

	@Id
	@GeneratedValue
	@Column(name = "USER_ROLE_ASSIGNMENT_ID")
	private Integer id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserProfile user;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	public UserRoleAssignment() {
		super();
	}

	public UserRoleAssignment(UserProfile user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
