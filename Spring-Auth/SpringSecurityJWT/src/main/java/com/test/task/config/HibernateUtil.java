package com.test.task.config;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Service that return SessionFactory
 * 
 * @author cortes
 *
 */

@Component
@Scope("application")
public class HibernateUtil {

	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

	private SessionFactory sessionFactory = buildSessionFactory();

	@PostConstruct
	private SessionFactory buildSessionFactory() {
		LOGGER.info("=============== Build Session Factory ===============");
		return new Configuration().configure().buildSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
