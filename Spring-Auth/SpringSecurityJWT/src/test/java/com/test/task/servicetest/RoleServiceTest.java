package com.test.task.servicetest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.test.task.components.entity.Role;
import com.test.task.components.service.RoleDao;
import com.test.task.service.RoleService;

public class RoleServiceTest {

	private RoleService roleService;
	private Role role;
	private RoleDao roleDao;
	private List<Role> roles;

	@Before
	public void setUp() {
		role = new Role("ROLE_ADMIN");
		roleDao = mock(RoleDao.class);
		roleService = new RoleService();
		roleService.setiRole(roleDao);
		roles = new ArrayList<>();
		roles.add(role);
	}

	@Test
	public void getRolesTest() {
		when(roleDao.getRoles()).thenReturn(roles);
		Assert.assertEquals(roleService.getRoles().size(), roles.size());
		verify(roleDao, times(1)).getRoles();
	}

	@Test
	@Rollback(false)
	public void deleteRoleTest() {
		doNothing().when(roleDao).deleteRole(1);
		roleService.deleteRole(1);
		verify(roleDao, times(1)).deleteRole(1);
	}

	@Test
	@Rollback(false)
	public void updateRoleTest() {
		doNothing().when(roleDao).updateRole(1, role);
		roleService.updateRole(1, role);
		verify(roleDao, times(1)).updateRole(1, role);
	}

	@Test
	public void getRoleByIdTest() {
		when(roleDao.getRoleById(1)).thenReturn(role);
		Assert.assertEquals(roleService.getRoleById(1).getName(), role.getName());
		verify(roleDao, times(1)).getRoleById(1);
	}

	@Test
	@Rollback(false)
	public void saveRoleTest() {
		doNothing().when(roleDao).saveRole(role);
		roleService.saveRole(role);
		verify(roleDao, times(1)).saveRole(role);
	}

}
