package org.example.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.example.jpa.domain.Credentials;
import org.example.jpa.domain.User;
import org.example.jpa.enums.UserType;


public class App {
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("jbd-pu");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			User user = new User();
			user.setFirstName("Peter");
			user.setLastName("Milanovich");
			user.setCreationTime(new Date());
			user.setDateofBirth(new Date());
			user.setUserType(UserType.EMPLOYEE);

			Credentials credentials = new Credentials();
			credentials.setUserName("peterm");
			credentials.setPassword("password");
			credentials.setUser(user);
			
			user.setCredentials(credentials);
			
			entityManager.persist(credentials);
			transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			entityManager.close();
			emf.close();
		}
	}
}
