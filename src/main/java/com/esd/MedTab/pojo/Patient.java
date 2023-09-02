package com.esd.MedTab.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Patient")
public class Patient {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "patient_id", unique = true)   
	private Integer id;
	@OneToOne
    @JoinColumn(name = "user_id")
	private User user;
	private String address;
	private LocalDateTime dob;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.EAGER)
	private List<Appointment> appointments = new ArrayList<>();
	public Patient() {
	}
	public Patient(User user) {
		super();
		this.user = user;
	}
	public Patient(User user, String address, LocalDateTime dob) {
		super();
		this.user = user;
		this.address = address;
		this.dob = dob;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getDob() {
		return dob;
	}
	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", user=" + user + ", address=" + address + ", dob=" + dob + "]";
	}
	
	
	
	
}
