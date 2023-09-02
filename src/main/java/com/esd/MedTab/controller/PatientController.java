package com.esd.MedTab.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.esd.MedTab.dao.AppointmentDAO;
import com.esd.MedTab.dao.PatientDAO;
import com.esd.MedTab.dao.UserDAO;
import com.esd.MedTab.pojo.Admin;
import com.esd.MedTab.pojo.Appointment;
import com.esd.MedTab.pojo.Doctor;
import com.esd.MedTab.pojo.Patient;
import com.esd.MedTab.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PatientController {
	
	

	@GetMapping("/updatePatient")
	public ModelAndView signUpPatientForm(ModelAndView model, PatientDAO patientdao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Patient") || user.getRole().equalsIgnoreCase("Admin")) ){
			model.setViewName("invalid");
			return model;
		}
		Patient patient = patientdao.getPatient(user);
		request.setAttribute("patient", patient);
		model.setViewName("update-patient");
		return model;
	}
	
	@GetMapping("/updatePatientAdmin")
	public ModelAndView updatePatientAdminForm(ModelAndView model, PatientDAO patientdao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user-object");
		String patientId = (String) request.getParameter("patientid");
		System.out.println("patient id: " + patientId);
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Patient") || user.getRole().equalsIgnoreCase("Admin")) ){
			model.setViewName("invalid");
			return model;
		}
		Patient patient = patientdao.getPatient(patientId);
		session.setAttribute("patient", patient);
		model.setViewName("update-patient");
		return model;
	}
	
	@PostMapping("/updatePatient")
	public String addPatient(ModelAndView model, PatientDAO patientdao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user-object");
		if(user.getRole().equalsIgnoreCase("admin")) {
			Patient patient = (Patient) session.getAttribute("patient");
			user = patient.getUser();
		}
		System.out.println("sesion User: "+user);
		String address =  request.getParameter("address");
		System.out.println("Address: " + address);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dob = LocalDateTime.parse(request.getParameter("dob"));
		System.out.println("dob: "+dob);
		long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
		System.out.println("phoneNo: " + phoneNo);
		patientdao.updatePatient(user,address,dob,phoneNo);
		user = (User) session.getAttribute("user-object");
		if(user.getRole().equalsIgnoreCase("admin")) {
			session.removeAttribute("patient");
			return "redirect:/home-admin";
		}
		return "redirect:/home-patient";
	}
	
	@GetMapping("/getPatientAppointments")
	public ModelAndView getAppointments(ModelAndView model, PatientDAO patientdao, AppointmentDAO appointmentdao, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Patient"))){
			model.setViewName("invalid");
			return model;
		}
		Patient patient = patientdao.getPatient(user);
		List<Appointment> appointments = appointmentdao.getAppointments(patient);
		model.addObject("appointments",appointments);
		model.setViewName("patient-appointments");
		return model;
	}
	
}
