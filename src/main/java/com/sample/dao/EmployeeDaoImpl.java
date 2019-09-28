package com.sample.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl {
	private static final Logger LOG = Logger.getLogger(EmployeeDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save() {
		Session session = sessionFactory.getCurrentSession();
		LOG.info("Session "+session);
	}

}
