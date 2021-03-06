package com.demonetization.pojo;
// default package
// Generated Apr 23, 2017 11:11:25 AM by Hibernate Tools 5.1.2.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resevation generated by hbm2java
 */
@Entity
@Table(name = "resevation")
public class Reservation implements java.io.Serializable {

	private Integer id;
	private Bank bank;
	private User user;
	private Set<ReservationDetails> reservationDetailses = new HashSet<ReservationDetails>(0);
	private String date;
	private String timeslot;
	private String status;
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Reservation() {
	}

	public Reservation(Bank bank, User user) {
		this.bank = bank;
		this.user = user;
	}

	public Reservation(Bank bank, User user, Set<ReservationDetails> reservationDetailses) {
		this.bank = bank;
		this.user = user;
		this.reservationDetailses = reservationDetailses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	@ManyToOne
	@JoinColumn(name = "Bank_id", nullable = false)
	public Bank getBank() {
		return this.bank;
	}

	@Column(name="date")
	public String getDate() {
		return date;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public void setDate(String string) {
		this.date = string;
	}

	@Column(name="timeslot")
	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	@ManyToOne
	@JoinColumn(name = "User_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "resevation")
	public Set<ReservationDetails> getReservationDetailses() {
		return this.reservationDetailses;
	}

	public void setReservationDetailses(Set<ReservationDetails> reservationDetailses) {
		this.reservationDetailses = reservationDetailses;
	}

}
