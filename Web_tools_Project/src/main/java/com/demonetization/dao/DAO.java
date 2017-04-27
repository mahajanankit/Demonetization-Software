package com.demonetization.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAO {	
	private static Configuration cfg = new Configuration();
	private static SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();

	public DAO(){}

	public static Session getSession() {
		Session session = sf.openSession();
		return session;
	}
	
	public void closeSession(Session session){
		session.close();
	}
}