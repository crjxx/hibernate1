package com.kaikeba.util;

import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration configuration = new Configuration().configure();
	private static SessionFactory sessionFactory = configuration.buildSessionFactory();
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void close(){
		sessionFactory.close();
	}
}
