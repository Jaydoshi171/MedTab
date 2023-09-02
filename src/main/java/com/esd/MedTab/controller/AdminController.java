package com.esd.MedTab.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.esd.MedTab.dao.AppointmentDAO;
import com.esd.MedTab.dao.DoctorDAO;
import com.esd.MedTab.dao.PatientDAO;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import com.esd.MedTab.pojo.*;

@Controller
public class AdminController {
	
	@GetMapping("/adminPatient")
	public ModelAndView adminPatient(ModelAndView model, PatientDAO patientdao, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Admin")) ){
			model.setViewName("invalid");
			return model;
		}
		List<Patient> patients = patientdao.getAllPatients();
		System.out.println("Patients are: "+ patients);
		request.setAttribute("allPatients",patients);
		model.setViewName("admin-patient");
		return model;
	}
	
	@GetMapping("/adminDoctor")
	public ModelAndView adminDoctor(ModelAndView model, DoctorDAO doctordao, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Admin")) ){
			model.setViewName("invalid");
			return model;
		}
		List<Doctor> doctors = doctordao.getAllDoctors();
		System.out.println("AllDoctors: "+doctors);
		request.setAttribute("allDoctors",doctors);
		model.setViewName("admin-doctor");
		return model;
	}
	
	
	@GetMapping("/getAllDoctors")
	public @ResponseBody List<Doctor> getAllDoctors(DoctorDAO doctordao,HttpServletRequest request) {
		List<Doctor> doctors = doctordao.getAllDoctors();
		return doctors;
		
	}
	
	@GetMapping("/deleteDoctor")
	public @ResponseBody boolean deleteDoctor(DoctorDAO doctordao,HttpServletRequest request) {
		String doctorId = request.getParameter("doctorId");
		System.out.println("doctorId: "+ doctorId);
		return doctordao.deleteDoctor(doctorId);
		
	}
	
	@GetMapping("/getAllPatient")
	public @ResponseBody List<Patient> getAllPatients(PatientDAO patientdao,HttpServletRequest request) {
		List<Patient> patients = patientdao.getAllPatients();
		return patients;
		
	}
	
	@GetMapping("/deletePatient")
	public @ResponseBody boolean deletePatient(PatientDAO patientdao,HttpServletRequest request) {
		String patientId = request.getParameter("patientId");
		System.out.println("patientId: "+ patientId);
		return patientdao.deletePatient(patientId);
		
	}
	
	
}
