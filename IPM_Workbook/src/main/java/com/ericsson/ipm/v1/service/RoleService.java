package com.ericsson.ipm.v1.service;

import java.util.List;

import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.dto.RoleDTO;

public interface RoleService {

	public Role save(Role entity);
	
	public void remove(Role entity);

	public Role update(Role entity);
	
	public Role findById(Integer id);
	
	public List<Role> findByCode(String code);
	
	public List<Role> getAllRoleWithKPIDetails();
	
	public List<RoleDTO> findAllRoleDto();
	
	public List<Role> findAll();
	
}
