package com.obaba.erp;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtil {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
		try {
			if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
				throw new NullPointerException("factory is not a hibernate factory");
			}
		} catch (Exception e) {
			throw e;
		}
		return entityManagerFactory.unwrap(SessionFactory.class);

	}
}


