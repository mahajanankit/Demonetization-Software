package com.demonetization.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.demonetization.pojo.ReservationDetails;

public class ReservationDetailsDAO extends DAO {

	@Autowired
	@Qualifier("reservation")
	ReservationDetails reservationDetails;

	public String createReservationDetails(ReservationDetails reservationDetails) {
		Session hibernatesession = getSession();
		String result = "Reservation is ";
		try {
			hibernatesession.beginTransaction();
			hibernatesession.saveOrUpdate(reservationDetails);
			hibernatesession.getTransaction().commit();
			result +=  " successfully on ";
		} catch (HibernateException e) {
			System.out.println("Error occured while creation" + e.getMessage());
			result = "Cannot add Federal Bank!" + e;
			hibernatesession.getTransaction().rollback();
		}finally{
			closeSession(hibernatesession);
		}
		return result;
	}
	
	
	public ArrayList<ReservationDetails> retrieveReservationDetails(int value){
		Session hibernatesession = getSession();
		ArrayList<ReservationDetails> reservationDetailses = new ArrayList<ReservationDetails>();
		try {
			
			Criteria crit = hibernatesession.createCriteria(ReservationDetails.class);
			crit.add(Restrictions.eq("id.resevationId",value));
			reservationDetailses = (ArrayList<ReservationDetails>) crit.list();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return reservationDetailses;
	}

}
