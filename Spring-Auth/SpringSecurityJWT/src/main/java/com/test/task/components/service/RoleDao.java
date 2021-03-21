package com.test.task.components.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.task.components.entity.Role;
import com.test.task.components.interfaces.IRole;
import com.test.task.config.HibernateUtil;

/**
 * CRUD for entity Role
 * 
 * @author cortes
 *
 */
@Repository
public class RoleDao implements IRole {

	private static final Logger LOGGER = Logger.getLogger(RoleDao.class);
	private final static String SQL_GET_ROLES = "SELECT * FROM roles";

	private HibernateUtil hibernateUtil;

	@Autowired
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	/**
	 * Method that return all roles in database
	 * 
	 * @return return - list of roles
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<Role> getRoles() {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			List<Role> result = session.createNativeQuery(SQL_GET_ROLES).getResultList();
			session.getTransaction().commit();
			return result;

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Delete role by it's id
	 * 
	 * @param id
	 *            id - id of role in database
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void deleteRole(Integer id) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			Role delRole = session.get(Role.class, id);
			session.delete(delRole);
			session.getTransaction().commit();

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Update role's information
	 * 
	 * @param id
	 *            id - id of role in database
	 * @param role
	 *            role - all information about role that will write to database
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void updateRole(Integer id, Role role) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			Role updateRole = session.get(Role.class, id);
			updateRole.setName(role.getName());
			session.update(updateRole);
			session.getTransaction().commit();
		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Get role by id
	 * 
	 * @param id
	 *            id - id of role in database
	 * @return return information about of role
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Role getRoleById(Integer id) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			Role result = session.get(Role.class, id);
			session.getTransaction().commit();
			return result;

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Save role to database
	 * 
	 * @param role
	 *            role - all information about role that will save to database
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void saveRole(Role role) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			session.save(new Role(role.getName()));
			session.getTransaction().commit();
		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}
	}

}
