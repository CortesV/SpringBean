package com.test.task.components.interfaces;

import java.util.List;

import com.test.task.components.entity.Role;

public interface IRole {
	/**
	 * Method that return all roles in database
	 * 
	 * @return return - list of roles
	 */
	public List<Role> getRoles();

	/**
	 * Delete role by it's id
	 * 
	 * @param id
	 *            id - id of role in database
	 */
	public void deleteRole(Integer id);

	/**
	 * Update role's information
	 * 
	 * @param id
	 *            id - id of role in database
	 * @param role
	 *            role - all information about role that will write to database
	 */
	public void updateRole(Integer id, Role role);

	/**
	 * Get role by id
	 * 
	 * @param id
	 *            id - id of role in database
	 * @return return information about of role
	 */
	public Role getRoleById(Integer id);

	/**
	 * Save role to database
	 * 
	 * @param role
	 *            role - all information about role that will save to database
	 */
	public void saveRole(Role role);
}
