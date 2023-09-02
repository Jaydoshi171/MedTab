package com.esd.MedTab.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.MedTab.pojo.Admin;
import com.esd.MedTab.pojo.Doctor;
import com.esd.MedTab.pojo.Patient;
import com.esd.MedTab.pojo.User;


@Component
public class UserDAO extends DAO {
	public UserDAO() {
		super();
	}
	
	public User findUser(String email){
		User user = null;
		try {
//			startTransaction();
//			Query query = getSession().createQuery("from User where username = :email");
//			query.setString("email", email);
//			user = (User) query.uniqueResult();
//			commit();
			
			startTransaction();
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("email",email));
			criteria.setMaxResults(1);
			user  = (User) criteria.uniqueResult();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return user;
	}
	
	public User authenticateUser(String email, String password){
		User user = null;
		try {
			startTransaction();
			Query query = getSession().createQuery("from User where email = :email and password = :password");
			query.setString("email", email);
			query.setString("password", password);
			user = (User) query.uniqueResult();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return user;
	}
	
	public User addUser(User user){
		try {
			startTransaction();
			getSession().save(user);
			commit();
			close();
			
		}catch (HibernateException e) {
			rollback();
		}
		return user;
	}
	
	public Patient addPatient(Patient patient){
		try {
			startTransaction();
			getSession().save(patient);
			commit();
			close();
			
		}catch (HibernateException e) {
			rollback();
		}
		return patient;
	}
	public Doctor addDoctor(Doctor doctor){
		try {
			startTransaction();
			getSession().save(doctor);
			commit();
			close();
			
		}catch (HibernateException e) {
			rollback();
		}
		return doctor;
	}
	public Admin addAdmin(Admin admin){
		try {
			startTransaction();
			getSession().save(admin);
			commit();
			close();
			
		}catch (HibernateException e) {
			rollback();
		}
		return admin;
	}
	
}
