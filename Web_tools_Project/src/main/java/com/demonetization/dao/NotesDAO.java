package com.demonetization.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.demonetization.pojo.Notes;

public class NotesDAO extends DAO {
	@Autowired
	@Qualifier("notes")
	Notes notes;

	public String createNotes(Notes notes) {
		Session hibernatesession = getSession();
		String result = "Notes with denominations ";
		try {
			hibernatesession.beginTransaction();
			hibernatesession.saveOrUpdate(notes);
			hibernatesession.getTransaction().commit();
			result +=  notes.getDenomination() +" created successfully.";
		} catch (HibernateException e) {
			System.out.println("Error occured while creation" + e.getMessage());
			result = "Cannot add Federal Bank!" + e;
			hibernatesession.getTransaction().rollback();
		}finally{
			closeSession(hibernatesession);
		}
		return result;
	}
	
	
	public Notes retrieveNotes(int value){
		Session hibernatesession = getSession();
		try {
			Criteria crit = hibernatesession.createCriteria(Notes.class);
			crit.add(Restrictions.eq("denomination",value));
			notes = (Notes) crit.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return notes;
	}
}
