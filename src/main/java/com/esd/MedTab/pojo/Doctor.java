package com.esd.MedTab.pojo;

import java.util.List;
import java.util.ArrayList;

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
@Table(name="Doctor")
public class Doctor {
	@Id
	
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "doctor_id", unique = true)   
	private Integer id;
	@OneToOne
    @JoinColumn(name = "user_id")
	private User user;
	@Column(name = "qualification")
	private String qualification;
	@Column(name = "department")
	private String department;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor",fetch = FetchType.EAGER)
	private List<Appointment> appointments = new ArrayList<>();
	
	public Doctor() {
	}
	
	public Doctor(User user, String qualification, String department) {
		super();
		this.user = user;
		this.qualification = qualification;
		this.department = department;
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", user=" + user + ", qualification=" + qualification + ", department=" + department
				+ "]";
	}


	
}
