package com.demonetization.dao;

import com.demonetization.pojo.Person;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonDAO extends DAO {

	@Autowired
	@Qualifier("person")
	Person person;

	public String createPerson(Person person) {
		Session hibernatesession = getSession();
		String result = "Person ";
		try {
			hibernatesession.beginTransaction();
			hibernatesession.saveOrUpdate(person);
			hibernatesession.getTransaction().commit();
			result += person.getFirstName()+" " +person.getLastName() + "created successfully";
		} catch (HibernateException e) {
			System.out.println("Error occured while creation" + e.getMessage());
			result = "Cannot add person! " + e;
			hibernatesession.getTransaction().rollback();
		}finally{
			closeSession(hibernatesession);
		}
		return result;
	}
	
	
	public Person retrievePerson(int value){
		Session hibernatesession = getSession();
		try {
			Criteria crit = hibernatesession.createCriteria(Person.class);
			crit.add(Restrictions.eq("ssn",value));
			person = (Person) crit.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return person;
	}


	
}
