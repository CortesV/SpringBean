package com.test.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.task.components.entity.Role;
import com.test.task.service.RoleService;


/**
 * Controller for CRUD of Role
 * 
 * @author cortes
 *
 */

@RestController
@RequestMapping(value = "/rest/test/v1/role/")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * Controller that return all roles in database
	 * 
	 * @return return - list of roles
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public List<Role> getRoles() {
		return roleService.getRoles();
	}

	/**
	 * Get role by id
	 * 
	 * @param id
	 *            id - id of role in database
	 * @return return information about of role
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	public Role getRoleById(@PathVariable("id") Integer id) {
		return roleService.getRoleById(id);
	}

	/**
	 * Save user to database
	 * 
	 * @param user
	 *            user - all information about user that will save to database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public void saveRole(@RequestBody Role role) {
		roleService.saveRole(role);
	}

	/**
	 * Controller that delete role by it's id
	 * 
	 * @param id
	 *            id - id of role in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void deleteRole(@PathVariable("id") Integer id) {
		roleService.deleteRole(id);
	}

	/**
	 * Update role's information
	 * 
	 * @param id
	 *            id - id of role in database
	 * @param role
	 *            role - all information about role that will write to database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
	public void updateRole(@PathVariable("id") Integer id, @RequestBody Role role) {
		roleService.updateRole(id, role);
	}
}
