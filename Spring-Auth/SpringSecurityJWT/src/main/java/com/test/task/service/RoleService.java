package com.test.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.task.components.entity.Role;
import com.test.task.components.interfaces.IRole;

/**
 * Service for CRUD of Role
 * 
 * @author cortes
 *
 */

@Service
public class RoleService {

	private IRole iRole;
	
	@Autowired
	public void setiRole(IRole iRole) {
		this.iRole = iRole;
	}

	/**
	 * Method that return all roles in database
	 * 
	 * @return return - list of roles
	 */
	public List<Role> getRoles() {
		return iRole.getRoles();
	}

	/**
	 * Delete role by it's id
	 * 
	 * @param id
	 *            id - id of role in database
	 */
	public void deleteRole(Integer id) {
		iRole.deleteRole(id);
	}

	/**
	 * Update role's information
	 * 
	 * @param id
	 *            id - id of role in database
	 * @param role
	 *            role - all information about role that will write to database
	 */
	public void updateRole(Integer id, Role role) {
		iRole.updateRole(id, role);
	}

	/**
	 * Get role by id
	 * 
	 * @param id
	 *            id - id of role in database
	 * @return return information about of role
	 */
	public Role getRoleById(Integer id) {
		return iRole.getRoleById(id);
	}

	/**
	 * Save role to database
	 * 
	 * @param role
	 *            role - all information about role that will save to database
	 */
	public void saveRole(Role role) {
		iRole.saveRole(role);
	}

}
