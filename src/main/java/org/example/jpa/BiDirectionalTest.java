package org.example.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.example.jpa.domain.Credentials;
import org.example.jpa.domain.User;


public class BiDirectionalTest {
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("jbd-pu");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			Credentials credential = entityManager.find(Credentials.class, new Long(2));
			System.err.println("================= From Credentials ===================");
			System.err.println("User Name: " + credential.getUserName());
			System.err.println("Password: " + credential.getPassword());
			System.err.println("First Name : " + credential.getUser().getFirstName());
			System.err.println("Last Name : " + credential.getUser().getLastName());

			User user = entityManager.find(User.class, new Long(2));
			System.err.println("================= From User ===================");
			System.err.println("First Name : " + user.getFirstName());
			System.err.println("Last Name : " + user.getLastName());
			System.err.println("User Name: " + user.getCredentials().getUserName());
			System.err.println("Password: " + user.getCredentials().getPassword());
		}
		catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			emf.close();
		}
	}
}
