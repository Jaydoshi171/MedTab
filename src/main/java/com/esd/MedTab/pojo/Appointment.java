package com.esd.MedTab.pojo;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Appointment")
public class Appointment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "appointment_id", unique = true)   
	private Integer id;
	@ManyToOne
    @JoinColumn(name = "patient_id")
	private Patient patient;
	@ManyToOne
    @JoinColumn(name = "doctor_id")
	private Doctor doctor;
	private LocalDateTime registerDate;
	private String appointmentDate;
	private String appointmentDescription;
	private String prescription;
	private String doctorComment;
	private String status;
	public Appointment() {
		
	}
	public Appointment(Doctor doctor,String appointmentDate, String appointmentDescription) {
		super();
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.appointmentDescription = appointmentDescription;
	}
	public Appointment(Patient patient, Doctor doctor, LocalDateTime registerDate, String appointmentDate,
			String appointmentDescription) {
		super();
		this.patient = patient;
		this.doctor = doctor;
		this.registerDate = registerDate;
		this.appointmentDate = appointmentDate;
		this.appointmentDescription = appointmentDescription;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentDescription() {
		return appointmentDescription;
	}
	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getDoctorComment() {
		return doctorComment;
	}
	public void setDoctorComment(String doctorComment) {
		this.doctorComment = doctorComment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", registerDate="
				+ registerDate + ", appointmentDate=" + appointmentDate + ", appointmentDescription="
				+ appointmentDescription + ", prescription=" + prescription + ", doctorComment=" + doctorComment
				+ ", status=" + status + "]";
	}
	
	
}
