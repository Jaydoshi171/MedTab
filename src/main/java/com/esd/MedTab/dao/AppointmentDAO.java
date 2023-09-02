package com.esd.MedTab.dao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import com.esd.MedTab.pojo.User;
import com.esd.MedTab.pojo.Appointment;
import com.esd.MedTab.pojo.Doctor;
import com.esd.MedTab.pojo.Patient;

import java.time.LocalDateTime;

@Component
public class AppointmentDAO extends DAO {
	public List<Doctor> getDoctors(){
		List<Doctor>  allDoctors = new ArrayList<Doctor>();
		try {
			startTransaction();
			allDoctors = getSession().createQuery("from Doctor",Doctor.class).list();
			commit();
			return allDoctors;
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return allDoctors;
	}
	
	public List<Appointment> getBookdedtime(String doctorId,String date){
		List<Appointment>  appointments = new ArrayList<Appointment>();
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where doctor_id = :doctorId and appointmentDate >= :currDate");
			LocalDateTime currDate = LocalDateTime.now();
			query.setString("doctorId",doctorId);
			query.setString("currDate", date);
			appointments = query.list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return appointments;
	}
	
	public List<Appointment> getAppointments(Patient patient){
		List<Appointment>  appointments = new ArrayList<Appointment>();
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where patient_id = :patientId");
			query.setString("patientId",patient.getId().toString());
			appointments = query.list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return appointments;
	}
	
	public List<Appointment> getPatientAppointments(Patient patient,String date){
		List<Appointment>  appointments = new ArrayList<Appointment>();
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where patient_id = :patientId and appointmentDate >= :currDate");
			query.setString("patientId",patient.getId().toString());
			query.setString("currDate",date);
			appointments = query.list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return appointments;
	}
	
	public List<Appointment> getAppointments(Doctor doctor){
		List<Appointment>  appointments = new ArrayList<Appointment>();
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where doctor_id = :doctorId");
			query.setString("doctorId",doctor.getId().toString());
			appointments = query.list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return appointments;
	}
	
	public List<Appointment> getDoctorAppointments(Doctor doctor, String date){
		List<Appointment>  appointments = new ArrayList<Appointment>();
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where doctor_id = :doctorId and appointmentDate >= :currDate");
			query.setString("doctorId",doctor.getId().toString());
			query.setString("currDate",date);
			appointments = query.list();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return appointments;
	}
	public boolean isAppointmentPresent(String doctorId, String appointmentTime){
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where doctor_id = :doctorId and appointmentDate = :currDate");
			LocalDateTime currDate = LocalDateTime.now();
			query.setString("doctorId",doctorId);
			query.setString("currDate", appointmentTime);
			Appointment appointment = (Appointment) query.uniqueResult();
			if(appointment!=null) {
				return true;
			}
			commit();
			close();
		}
		catch(HibernateException e) {
			rollback();
			return false;
		}
		return false;
	}
	
	public boolean bookAppointment(Appointment appointment) {
		try {
			startTransaction();
			System.out.println("In dao: "+appointment);
			getSession().save(appointment);
			commit();
			close();
		}
		catch(HibernateException e) {
			rollback();
			return false;
		}
		return true;
	}
	
	public Appointment getAppointment (String appointmentId){
		Appointment appointment = null;
		try {
			startTransaction();
			Query query = getSession().createQuery("from Appointment where appointment_id = :appointment_id");
			query.setString("appointment_id", appointmentId);
			appointment = (Appointment) query.uniqueResult();
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
		}
		return appointment;
	}
	
	public boolean updateAppointment(Appointment appointment){
		try {

			startTransaction();
			Query query = getSession().createQuery("update Appointment a set a.doctorComment = :doctorComment, a.prescription = :prescription where a.id=:appointmentId");
			query.setString("doctorComment", appointment.getDoctorComment());
			query.setString("prescription", appointment.getPrescription());
			query.setString("appointmentId", appointment.getId().toString());
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
