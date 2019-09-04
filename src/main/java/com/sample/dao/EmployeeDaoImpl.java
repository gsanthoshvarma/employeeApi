package com.sample.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save() {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("Session "+session);
	}

}
