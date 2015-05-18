package com.ericsson.ipm.v1.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.ipm.v1.dao.RoleDAO;
import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.dto.RoleDTO;


@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;
	
	@Override
	public Role save(Role entity) {
		return roleDAO.save(entity);
	}

	@Override
	public void remove(Role entity) {
		 roleDAO.remove(entity);
		
	}

	@Override
	public Role update(Role entity) {
		return roleDAO.update(entity);
	}

	@Override
	public Role findById(Integer id) {
		return roleDAO.findById(id);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public List<Role> findByCode(String code) {
		return roleDAO.findByCode(code);
	}
	
	@Override
	public List<RoleDTO> findAllRoleDto() {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		List<Role> roles = roleDAO.findAll();
		
		for(Role role : roles){
			RoleDTO dest = new RoleDTO();
			dest.setId(role.getId());
			dest.setName(role.getName());
			dest.setCode(role.getCode());
			roleDTOs.add(dest);
		}
		return roleDTOs;
	}
	
	@Override
	public List<Role> findAll() {
		return roleDAO.findAll();
	}
	
	
	public List<Role> getAllRoleWithKPIDetails(){
		
		roleDAO.getAllRoleWithKPIDetails();
		
		return null;
	}
	
	

}
