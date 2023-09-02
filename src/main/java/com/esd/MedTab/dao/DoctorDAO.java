package com.esd.MedTab.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.esd.MedTab.pojo.Doctor;
import com.esd.MedTab.pojo.Patient;
import com.esd.MedTab.pojo.User;

public class DoctorDAO extends DAO {
	
	public List<Doctor> getAllDoctors(){
		List<Doctor> allDoctor = null;
		try {
			startTransaction();
			allDoctor= getSession().createQuery("from Doctor",Doctor.class).list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return allDoctor;
	}
	
	public Doctor getDoctor(String doctorId){
		Doctor doctor = null;
		try {
			startTransaction();
			Query query = getSession().createQuery("from Doctor where doctor_id = :doctor_id");
			query.setString("doctor_id", doctorId);
			doctor = (Doctor) query.uniqueResult();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return doctor;
	}
	
	public Doctor getDoctor(User user){
		Doctor doctor = null;
		try {
			startTransaction();
			Query query = getSession().createQuery("from Doctor where user_id = :user");
			query.setString("user", user.getId().toString());
			doctor = (Doctor) query.uniqueResult();
			commit();
			close();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return doctor;
	}
	
	public boolean deleteDoctor(String doctorId){
		Doctor doctor = null;
		try {
			doctor = getDoctor(doctorId);
			User user = doctor.getUser();
			System.out.println("doctor: "+ doctor);
			startTransaction();
			getSession().delete(doctor);
			getSession().delete(user);
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
			return false;
		}
		return true;
	}
	
	public boolean updateDoctor(User user,String department,String qualification,long phoneNo){
		try {
			Doctor doctor = getDoctor(user); 
			if(phoneNo!=0) {
				user.setPhoneNo(phoneNo);
			}
			if(department!=null) {
				doctor.setDepartment(department);
			}
			if(qualification!=null) {
				doctor.setQualification(qualification);
			}
			System.out.println("updated User: " + user);
			System.out.println("updated doctor: "+ doctor);
			startTransaction();
			getSession().update(user);
			Query query = getSession().createQuery("update Doctor d set d.department = :department, d.qualification = :qualification where d.id=:doctorID");
			query.setString("department", doctor.getDepartment());
			query.setString("qualification", doctor.getQualification());
			query.setString("doctorID", doctor.getId().toString());
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
	
}
