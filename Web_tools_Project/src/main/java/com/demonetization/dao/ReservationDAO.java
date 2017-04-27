package com.demonetization.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.Expression;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.demonetization.pojo.Reservation;
import com.demonetization.pojo.User;

public class ReservationDAO extends DAO {
	@Autowired
	@Qualifier("reservation")
	Reservation reservation;

	public String createReservation(Reservation reservation) {
		Session hibernatesession = getSession();
		String result = "Reservation has been made with ";
		try {
			hibernatesession.beginTransaction();
			hibernatesession.saveOrUpdate(reservation);
			hibernatesession.getTransaction().commit();
			result +=  reservation.getBank() +" successfully on "+ reservation.getDate() + " at " + reservation.getTimeslot();
		} catch (HibernateException e) {
			System.out.println("Error occured while creation" + e.getMessage());
			result = "Cannot add Federal Bank!" + e;
			hibernatesession.getTransaction().rollback();
		}finally{
			closeSession(hibernatesession);
		}
		return result;
	}
	
	
	public Reservation retrieveReservation(int value){
		Session hibernatesession = getSession();
		try {
			Criteria crit = hibernatesession.createCriteria(Reservation.class);
			crit.add(Restrictions.eq("id",value));
			reservation = (Reservation) crit.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return reservation;
	}


	public ArrayList<Reservation> getReservations(User user) {
		// TODO Auto-generated method stub
		Session hibernatesession = getSession();
		
		ArrayList<Reservation> reservations= new ArrayList<Reservation>();
		
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df = new SimpleDateFormat("hh");
			Date date = new Date();
			String time = String.valueOf(Integer.parseInt(df.format(date)));
			String endtime = String.valueOf((Integer.parseInt(time)+1));
			Criteria crit = hibernatesession.createCriteria(Reservation.class);
			
			
			crit.add(Restrictions.eq("bank.id",user.getBank().getId()));
			
			
			crit.add(Restrictions.like("date",dateFormat.format(date).trim()));
			
			
			crit.add(Restrictions.eq("timeslot",(time+"-"+endtime)));
			
			
			crit.add(Restrictions.ne("status","complete"));
			
			reservations = (ArrayList<Reservation>) crit.list();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return reservations;
	}


	public ArrayList<Reservation> retrieveReservationAllforCustomer(int id) {
		// TODO Auto-generated method stub
		Session hibernatesession = getSession();
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try {
			Criteria crit = hibernatesession.createCriteria(Reservation.class);
			crit.add(Restrictions.eq("user.id",id));
			reservations = (ArrayList<Reservation>) crit.list();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return reservations;
	}


	public ArrayList<Reservation> getReservations(String dateInString, String timeslot, User user) {
		// TODO Auto-generated method stub
		Session hibernatesession = getSession();
ArrayList<Reservation> reservations= new ArrayList<Reservation>();
		
		try {
			
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 
			 Date date = formatter.parse(dateInString);
		     System.out.println(formatter.format(date));

			
			Criteria crit = hibernatesession.createCriteria(Reservation.class);
			crit.add(Restrictions.eq("bank.id",user.getBank().getId()));
			System.out.println(user.getBank().getId());
			crit.add(Restrictions.like("date",formatter.format(date).trim()));
			
			
			crit.add(Restrictions.eq("timeslot",timeslot));
			
			
			reservations = (ArrayList<Reservation>) crit.list();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		return reservations;
	}
}
