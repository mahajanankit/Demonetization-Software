package com.demonetization.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.demonetization.pojo.User;

public class UserDAO extends DAO{
	
	@Autowired
	@Qualifier("user")
	User user;

	public String createUser(User user) {
		Session hibernatesession = getSession();
		String result = "User ";
		try {
			hibernatesession.beginTransaction();
			hibernatesession.saveOrUpdate(user);
			hibernatesession.getTransaction().commit();
			result +=  user.getPerson().getFirstName() +"created successfully.";
		} catch (HibernateException e) {
			System.out.println("Error occured while creation" + e.getMessage());
			result = "Cannot add Bank Employee! " + e;
			hibernatesession.getTransaction().rollback();
		}finally{
			closeSession(hibernatesession);
		}
		return result;
	}
	
	
	public User retrieveUser(int value){
		Session hibernatesession = getSession();
		try {
			Criteria crit = hibernatesession.createCriteria(User.class);
			crit.add(Restrictions.eq("id",value));
			user = (User) crit.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}
		return user;
	}


	public boolean authenticateUser(int id, String password) {
		// TODO Auto-generated method stub
		
		user = retrieveUser(id);
		if(user.getPassword().equals(password)){
			return true;
		}
		
		return false;
	}
}
