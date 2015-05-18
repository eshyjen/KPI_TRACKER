package com.ericsson.ipm.v1.dao;

import java.util.List;

import com.ericsson.ipm.v1.domain.Role;

public interface RoleDAO {

	
	public Role save(Role entity);
	
	public void remove(Role entity);

	public Role update(Role entity);

	public Role findById(Integer id);
	
	public List<Role> findByCode(String code);
	
	public List<Role> getAllRoleWithKPIDetails();
	
	public List<Role> findAll();
	
}
