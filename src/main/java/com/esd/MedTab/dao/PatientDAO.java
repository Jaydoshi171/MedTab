package com.esd.MedTab.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.MedTab.pojo.Doctor;
import com.esd.MedTab.pojo.Patient;
import com.esd.MedTab.pojo.User;

@Component
public class PatientDAO extends DAO {
		public boolean updatePatient(User user,String address,LocalDateTime dob,long phoneNo){
			try {
				Patient patient = getPatient(user); 
				if(phoneNo!=0) {
					user.setPhoneNo(phoneNo);
				}
				if(address!=null) {
					patient.setAddress(address);
				}
				if(dob!=null) {
					patient.setDob(dob);
				}
				System.out.println("updated User: " + user);
				System.out.println("updated Patient: "+ patient);
				startTransaction();
				getSession().update(user);
				Query query = getSession().createQuery("update Patient p set p.address = :address, p.dob = :dob where p.id=:patientID");
				query.setString("address", patient.getAddress());
				query.setString("dob", patient.getDob().toString());
				query.setString("patientID", patient.getId().toString());
				int updatedField = query.executeUpdate();
				System.out.println("updated fields in database: "+ updatedField);
				commit();
				
			}
			catch(HibernateException e) {
				rollback();
				return false;
			}
			return true;
		}
	
	public Patient getPatient(User user){
		Patient patient = null;
		try {
			startTransaction();
			Query query = getSession().createQuery("from Patient where user_id = :user");
			query.setString("user", user.getId().toString());
			patient = (Patient) query.uniqueResult();
			commit();
			close();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return patient;
	}
	
	public List<Patient> getAllPatients(){
		List<Patient> allPatients = null;
		try {
			startTransaction();
			allPatients = getSession().createQuery("from Patient",Patient.class).list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return allPatients;
	}
	
	public Patient getPatient(String patientId){
		Patient patient = null;
		try {
			startTransaction();
			Query query = getSession().createQuery("from Patient where patient_id = :patient_id");
			query.setString("patient_id", patientId);
			patient = (Patient) query.uniqueResult();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return patient;
	}
	
	
	
	public boolean deletePatient(String patientId){
		Patient patient = null;
		try {
			patient = getPatient(patientId);
			User user = patient.getUser();
			System.out.println("patient: "+ patient);
			startTransaction();
			getSession().delete(patient);
			getSession().delete(user);
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
			return false;
		}
		return true;
	}
}
