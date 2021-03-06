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
 * Notes generated by hbm2java
 */
@Entity
@Table(name = "notes")
public class Notes implements java.io.Serializable {

	private Integer id;
	private Integer denomination;
	private Set<ReservationDetails> reservationDetailses = new HashSet<ReservationDetails>(0);

	public Notes() {
	}

	public Notes(Integer denomination, Set<ReservationDetails> reservationDetailses) {
		this.denomination = denomination;
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

	@Column(name = "denomination")
	public Integer getDenomination() {
		return this.denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	@OneToMany(mappedBy = "notes")
	public Set<ReservationDetails> getReservationDetailses() {
		return this.reservationDetailses;
	}

	public void setReservationDetailses(Set<ReservationDetails> reservationDetailses) {
		this.reservationDetailses = reservationDetailses;
	}

}
