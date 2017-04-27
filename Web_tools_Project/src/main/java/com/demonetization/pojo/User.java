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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements java.io.Serializable {

	private Integer id;
	private Bank bank;
	private Person person;
	private String email;
	private String password;
	private String role;
	private Set<Reservation> resevations = new HashSet<Reservation>(0);

	public User() {
	}

	public User(Bank bank, Person person, String email, String password, String role) {
		this.bank = bank;
		this.person = person;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(Bank bank, Person person, String email, String password, String role, Set<Reservation> resevations) {
		this.bank = bank;
		this.person = person;
		this.email = email;
		this.password = password;
		this.role = role;
		this.resevations = resevations;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Bank_id")
	public Bank getBank() {
		return this.bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@ManyToOne
	@JoinColumn(name = "Person_id", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "email", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(mappedBy = "user")
	public Set<Reservation> getResevations() {
		return this.resevations;
	}

	public void setResevations(Set<Reservation> resevations) {
		this.resevations = resevations;
	}

}
