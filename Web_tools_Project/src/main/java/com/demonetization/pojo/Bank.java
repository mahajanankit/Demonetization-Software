package com.demonetization.pojo;
// default package
// Generated Apr 23, 2017 11:11:25 AM by Hibernate Tools 5.1.2.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bank generated by hbm2java
 */
@Entity
@Table(name = "bank")
public class Bank implements java.io.Serializable {

	private Integer id;
	private String bankName;
	private int balance;
	private Set<Reservation> resevations = new HashSet<Reservation>(0);
	private Set<User> users = new HashSet<User>(0);

	public Bank() {
	}

	public Bank(String bankName, int balance) {
		this.bankName = bankName;
		this.balance = balance;
	}

	public Bank(String bankName, int balance, Set<Reservation> resevations, Set<User> users) {
		this.bankName = bankName;
		this.balance = balance;
		this.resevations = resevations;
		this.users = users;
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

	@Column(name = "BankName", nullable = false, length = 45)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "Balance", nullable = false)
	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bank")
	public Set<Reservation> getResevations() {
		return this.resevations;
	}

	public void setResevations(Set<Reservation> resevations) {
		this.resevations = resevations;
	}

	@OneToMany(mappedBy = "bank")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
