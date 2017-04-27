package com.demonetization.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.demonetization.pojo.Bank;

public class BankDAO extends DAO{
	
	@Autowired
	@Qualifier("bank")
	Bank bank;

	public String createBank(Bank bank) {
		Session hibernatesession = getSession();
		String result = "Bank ";
		try {
			hibernatesession.beginTransaction();
			hibernatesession.saveOrUpdate(bank);
			hibernatesession.getTransaction().commit();
			result +=  bank.getBankName() +"created successfully.";
		} catch (HibernateException e) {
			System.out.println("Error occured while creation" + e.getMessage());
			result = "Cannot add Bank Employee! " + e;
			hibernatesession.getTransaction().rollback();
		}finally{
			closeSession(hibernatesession);
		}
		return result;
	}
	
	
	public Bank retrieveBank(int value){
		Session hibernatesession = getSession();
		try {
			Criteria crit = hibernatesession.createCriteria(Bank.class);
			crit.add(Restrictions.eq("id",value));
			bank = (Bank) crit.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		
		return bank;
	}


	public ArrayList<Bank> getAllUsers() {
		// TODO Auto-generated method stub
		ArrayList<Bank> bankList = new ArrayList<Bank>();
		Session hibernatesession = getSession();
		try {
			Criteria crit = hibernatesession.createCriteria(Bank.class);
			bankList = (ArrayList<Bank>) crit.list();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occured while retrieval" + e.getMessage());
		}finally{
			closeSession(hibernatesession);
		}
		
		return bankList;
		
	}
}