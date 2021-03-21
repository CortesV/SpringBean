package com.test.task.components.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.task.components.entity.User;
import com.test.task.components.interfaces.IUser;
import com.test.task.config.HibernateUtil;

/**
 * CRUD for entity User
 * 
 * @author cortes
 *
 */
@Repository
public class UserDao implements IUser {

	private static final Logger LOGGER = Logger.getLogger(UserDao.class);
	private final static String SQL_GET_USERS = "SELECT * FROM user";
	private final static String SQL_GET_USER = "from User user where userName = :username";
	private final static String USERNAME = "username";

	private HibernateUtil hibernateUtil;

	@Autowired
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	/**
	 * Get user by username
	 * 
	 * @param username
	 *            username - username of user in database
	 * @return return information about of user
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public User findByUsername(String username) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();

			Query query = session.createQuery(SQL_GET_USER);
			query.setParameter(USERNAME, username);
			User result = (User) query.uniqueResult();

			session.getTransaction().commit();
			return result;

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Method that return all users in database
	 * 
	 * @return return - list of users
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public List<User> getUsers() {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			List<User> result = (ArrayList<User>) session.createNativeQuery(SQL_GET_USERS).list();
			session.getTransaction().commit();
			return result;

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Delete user by it's id
	 * 
	 * @param id
	 *            id - id of user in database
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void deleteUser(Integer id) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			User delUser = session.get(User.class, id);
			session.delete(delUser);
			session.getTransaction().commit();

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Update user's information
	 * 
	 * @param id
	 *            id - id of user in database
	 * @param user
	 *            user - all information about user that will write to database
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void updateUser(Integer id, User user) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			User updateUser = session.get(User.class, id);
			updateUser.setUserName(user.getUserName());
			updateUser.setPassword(user.getPassword());
			updateUser.setIsActive(user.getIsActive());
			session.update(updateUser);
			session.getTransaction().commit();
		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Get user by id
	 * 
	 * @param id
	 *            id - id of user in database
	 * @return return information about of user
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public User getUserById(Integer id) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			User result = session.get(User.class, id);
			session.getTransaction().commit();
			return result;

		} catch (Exception e) {

			LOGGER.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Save user to database
	 * 
	 * @param user
	 *            user - all information about user that will save to database
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void saveUser(User user) {
		try (Session session = hibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			session.save(new User(user.getUserName(), user.getPassword(), user.getIsActive()));
			session.getTransaction().commit();
		} catch (Exception e) {

			LOGGER.error(e.getMessage());
		}
	}

}
