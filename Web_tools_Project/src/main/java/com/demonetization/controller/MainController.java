package com.demonetization.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.demonetization.dao.*;
import com.demonetization.pojo.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	 
	@Autowired
	@Qualifier("personDAO")
	PersonDAO personDao;
	
	@Autowired
	@Qualifier("bankDAO")
	BankDAO bankDao;
	
	
	@Autowired
	@Qualifier("notesDAO")
	NotesDAO noteskDao;
	
	@Autowired
	@Qualifier("userDAO")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("reservationDAO")
	ReservationDAO reservationDAO;
	
	@Autowired
	@Qualifier("reservationDetailsDAO")
	ReservationDetailsDAO reservationDetailsDAO;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@RequestMapping(value = "/createReservation.htm", method = RequestMethod.POST)
	public ModelAndView createReservation(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();	
		
		if(request.getSession().getAttribute("userId") != null){
		
			
			Bank bank = bankDao.retrieveBank(Integer.parseInt(request.getParameter("bankId")));		
			User user = userDao.retrieveUser(Integer.parseInt(request.getParameter("id")));
			
			
			Notes notes5 = noteskDao.retrieveNotes(500);
			Notes notes10 = noteskDao.retrieveNotes(1000);
			
			int quantity5 = Integer.parseInt(request.getParameter("five"));
			int quantity10 = Integer.parseInt(request.getParameter("thousand"));
			
			
			ReservationDetailsId reservationDetailsId5 = new ReservationDetailsId();
			reservationDetailsId5.setNotesId(notes5.getId());
			reservationDetailsId5.setQuantity(quantity5);
			
			ReservationDetailsId reservationDetailsId10 = new ReservationDetailsId();
			reservationDetailsId10.setNotesId(notes10.getId());
			reservationDetailsId10.setQuantity(quantity10);
			
			
			ReservationDetails reservationDetails5= new ReservationDetails();
			reservationDetails5.setId(reservationDetailsId5);
			
			
			ReservationDetails reservationDetails10= new ReservationDetails();
			reservationDetails10.setId(reservationDetailsId10);
			
			
			Reservation reservation = new Reservation();	
			reservation.setBank(bank);
			reservation.setDate(request.getParameter("date"));
			reservation.setTimeslot(request.getParameter("timeslot"));
			reservation.setUser(user);
			reservation.setStatus("incomplete");
			reservation.getReservationDetailses().add(reservationDetails5);
			reservation.getReservationDetailses().add(reservationDetails10);
			
			user.getResevations().add(reservation);
			userDao.createUser(user);
			
			
			reservationDAO.createReservation(reservation);
			reservationDetails5.setResevation(reservation);
			reservationDetails10.setResevation(reservation);
			
			reservationDetailsId10.setResevationId(reservation.getId());
			reservationDetailsId5.setResevationId(reservation.getId());
			
			reservationDetailsDAO.createReservationDetails(reservationDetails5);
			reservationDetailsDAO.createReservationDetails(reservationDetails10);
			
			modelAndView.setViewName("result_reservation");
			
		}
		else{
			modelAndView.setViewName("login");
			
			
		}	
		
		return modelAndView;
	}
	

	
	
	
	@RequestMapping(value = "/updateUser.htm", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("userId") != null){
		String role = request.getParameter("role");
		Person person = personDao.retrievePerson(Integer.parseInt(request.getParameter("ssn")));
		String result = " ";
		
		User user = userDao.retrieveUser(Integer.parseInt(request.getParameter("id")));
			
		person.setLastName(request.getParameter("lname"));
		person.setSsn(Integer.parseInt(request.getParameter("ssn")));
		person.setFirstName(request.getParameter("fname"));
		
	//	user.setBank(bankDao.retrieveBank(Integer.parseInt(request.getParameter("bankId"))));
		user.setEmail(request.getParameter("user_email"));
		user.setPassword(request.getParameter("psw"));
		user.setPerson(person);
		user.setRole(role);
		
		result = personDao.createPerson(person);
		result += " Also " + userDao.createUser(user);
		
		
		
		if(user.getRole().equalsIgnoreCase("customer")){

			ArrayList<Reservation> reservations = reservationDAO.retrieveReservationAllforCustomer(user.getId());
			modelAndView.addObject("reservations",reservations);
			modelAndView.setViewName("user-dashboard");
		}
		else if(user.getRole().equalsIgnoreCase("employee")){
			ArrayList<Reservation> reservations = reservationDAO.getReservations(user);
			modelAndView.addObject("reservations",reservations);
			modelAndView.setViewName("employee-dashboard");
		}
		
		}
		else{
			modelAndView.setViewName("login");
			
			
		}
		
		
		
		return modelAndView;
	}
	
	
	
	
	
	@RequestMapping(value = "/createUser.htm", method = RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		String role = request.getParameter("role");
		Person person = personDao.retrievePerson(Integer.parseInt(request.getParameter("ssn")));
		String result = " ";
		
		
		if(person == null){
			User user = new User();	
			
			person = new Person();
			person.setLastName(request.getParameter("lname"));
			person.setSsn(Integer.parseInt(request.getParameter("ssn")));
			person.setFirstName(request.getParameter("fname"));
			
//			user.setBank(bankDao.retrieveBank(Integer.parseInt(request.getParameter("bankId"))));
			user.setEmail(request.getParameter("user_email"));
			user.setPassword(request.getParameter("psw"));
			user.setPerson(person);
			user.setRole(role);
			
			person.getUsers().add(user);
			result = personDao.createPerson(person);
			result += " Also " + userDao.createUser(user);
		}
		else{
			boolean userExists = false;
			for(User u:person.getUsers()){
				if(u.getRole().equalsIgnoreCase(role)){
					userExists = true;
					modelAndView.setViewName("result");
					modelAndView.addObject("result","User already exists");
					return modelAndView;
				}
			};
			
			if(!userExists){
				User user = new User();	
				
	//			user.setBank(bankDao.retrieveBank(Integer.parseInt(request.getParameter("bankId"))));
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("psw"));
				user.setPerson(personDao.retrievePerson(Integer.parseInt(request.getParameter("personId"))));
				user.setRole(role);
				person.getUsers().add(user);
				personDao.createPerson(person);
				result = userDao.createUser(user);
			}
			}
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView loginget(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		int id = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		
		User user = userDao.retrieveUser(id);
		
		if(user.getRole().equalsIgnoreCase("customer")){

			ArrayList<Reservation> reservations = reservationDAO.retrieveReservationAllforCustomer(id);
			modelAndView.addObject("reservations",reservations);
			modelAndView.setViewName("user-dashboard");
		}
		else if(user.getRole().equalsIgnoreCase("employee")){
			ArrayList<Reservation> reservations = reservationDAO.getReservations(user);
			modelAndView.addObject("reservations",reservations);
			modelAndView.setViewName("employee-dashboard");
		}
		
		
		
		
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		int userid = Integer.parseInt(request.getParameter("id"));
		boolean isAuthenticated = userDao.authenticateUser(userid , request.getParameter("password"));
		
		if(isAuthenticated){
			request.getSession().setAttribute("userId", userid);
			User user = userDao.retrieveUser(Integer.parseInt(request.getParameter("id")));
			

			if(user.getRole().equalsIgnoreCase("customer")){
				int id = Integer.parseInt(request.getSession().getAttribute("userId").toString());
				ArrayList<Reservation> reservations = reservationDAO.retrieveReservationAllforCustomer(id);
				modelAndView.addObject("reservations",reservations);
				modelAndView.setViewName("user-dashboard");
			}
			else if(user.getRole().equalsIgnoreCase("employee")){
				ArrayList<Reservation> reservations = reservationDAO.getReservations(user);
				modelAndView.addObject("reservations",reservations);
				modelAndView.setViewName("employee-dashboard");
				}
			
			else if(user.getRole().equalsIgnoreCase("manager"))
				modelAndView.setViewName("manager-home");
			
			else if(user.getRole().equalsIgnoreCase("admin"))
				modelAndView.setViewName("admin-home");
			
		}
		else{
			modelAndView.setViewName("result");
			modelAndView.addObject("result","Unauthenticated user.");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
	ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("userId") != null){
		modelAndView.setViewName("logout");
	}
	else{
		modelAndView.setViewName("login");	
	}
		return modelAndView;
	
	}
	
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		request.getSession().invalidate();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value ="/signup.htm", method = RequestMethod.GET)
	public ModelAndView signup(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		ArrayList<Bank> bankList = bankDao.getAllUsers();
		
		modelAndView.addObject("bankList",bankList);
		modelAndView.setViewName("signup");
		return modelAndView;
	}
	
	
	@RequestMapping(value ="/user-details.htm", method = RequestMethod.GET)
	public ModelAndView user_details(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(request.getSession().getAttribute("userId") != null){
		
		User user = userDao.retrieveUser(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
//		System.out.println(user.getPerson().getId());
		Person person = personDao.retrievePerson(user.getPerson().getId());
		modelAndView.addObject("person",person);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user");
		}
		else{
			modelAndView.setViewName("login");	
		}
		
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value ="/employee-details.htm", method = RequestMethod.GET)
	public ModelAndView employee_details(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(request.getSession().getAttribute("userId") != null){
		
		User user = userDao.retrieveUser(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
//		System.out.println(user.getPerson().getId());
		Person person = personDao.retrievePerson(user.getPerson().getId());
		modelAndView.addObject("person",person);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("employee_details");
		}
		else{
			modelAndView.setViewName("login");	
		}
		
		
		return modelAndView;
	}
	
	
	
	
	
	
	@RequestMapping(value ="/placeOrder.htm", method = RequestMethod.GET)
	public ModelAndView place_order(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("userId") != null){
		ArrayList<Bank> bankList = bankDao.getAllUsers();
		
		modelAndView.addObject("banks",bankList);
		modelAndView.setViewName("placeOrder");
		
	}
		else{
			modelAndView.setViewName("login");	
		}
		
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(value ="/processRequest.htm", method = RequestMethod.POST)
	public ModelAndView processRequest(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("userId") != null){
		Reservation reservation = reservationDAO.retrieveReservation(Integer.parseInt(request.getParameter("reservation")));
		User user = reservation.getUser();
		ArrayList<ReservationDetails> reservationDetails = reservationDetailsDAO.retrieveReservationDetails(reservation.getId());
		
		modelAndView.addObject("reservation1",reservationDetails.get(0));
		modelAndView.addObject("reservation2",reservationDetails.get(1));
		modelAndView.addObject("reservation",reservation);
		modelAndView.addObject("user",user);
		modelAndView.addObject("reservation",reservation);
		modelAndView.setViewName("processRequest");
		
	}
	else{
		modelAndView.setViewName("login");	
	}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value ="/searchOrder.htm", method = RequestMethod.GET)
	public ModelAndView searchOrder(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("userId") != null){
		
		modelAndView.setViewName("searchOrder");
		}
		else{
			modelAndView.setViewName("login");	
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value ="/searchResult.htm", method = RequestMethod.POST)
	public ModelAndView searchResult(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("userId") != null){
		String date= request.getParameter("date");
		String timeslot = request.getParameter("timeslot"); 
		User user = userDao.retrieveUser(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
		
		ArrayList<Reservation> reservations = reservationDAO.getReservations(date,timeslot,user);
		modelAndView.addObject("reservations",reservations);
		
		modelAndView.setViewName("searchResult");
		
		}
		else{
			modelAndView.setViewName("login");	
		}
		
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value ="/completeRequest.htm", method = RequestMethod.POST)
	public ModelAndView processRequestSearch(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(request.getSession().getAttribute("userId") != null){
		
		Reservation reservation = reservationDAO.retrieveReservation(Integer.parseInt(request.getParameter("reservationid")));
		reservation.setStatus("complete");
		reservationDAO.createReservation(reservation);
		User user = userDao.retrieveUser(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
		ArrayList<Reservation> reservations = reservationDAO.getReservations(user);
		modelAndView.addObject("reservations",reservations);
		modelAndView.setViewName("employee-dashboard");
		
		
		}
		else{
			modelAndView.setViewName("login");	
		}
		
		return modelAndView;
	}
	
}