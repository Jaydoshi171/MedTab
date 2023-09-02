package com.esd.MedTab.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.esd.MedTab.dao.AppointmentDAO;
import com.esd.MedTab.dao.DoctorDAO;
import com.esd.MedTab.dao.PatientDAO;
import com.esd.MedTab.pojo.Appointment;
import com.esd.MedTab.pojo.Doctor;
import com.esd.MedTab.pojo.Patient;
import com.esd.MedTab.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class DoctorController {

	@GetMapping("/getDoctorAppointments")
	public ModelAndView getAppointments(ModelAndView model, DoctorDAO doctordao, AppointmentDAO appointmentdao, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Doctor"))){
			model.setViewName("invalid");
			return model;
		}
		Doctor doctor = doctordao.getDoctor(user);
		List<Appointment> appointments = appointmentdao.getAppointments(doctor);
		model.addObject("appointments",appointments);
		model.setViewName("doctor-appointments");
		return model;
	}
	
	@GetMapping("/updateDoctor")
	public ModelAndView updateDoctorForm(ModelAndView model, DoctorDAO doctordao, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Doctor") || user.getRole().equalsIgnoreCase("Admin")) ){
			model.setViewName("invalid");
			return model;
		}
		Doctor doctor = doctordao.getDoctor(user);
		request.setAttribute("doctor", doctor);
		model.setViewName("update-doctor");
		return model;
	}
	
	@GetMapping("/updateDoctorAdmin")
	public ModelAndView updateDoctorAdminForm(ModelAndView model, DoctorDAO doctordao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user-object");
		String doctorId = (String) request.getParameter("doctorid");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Admin")) ){
			model.setViewName("invalid");
			return model;
		}
		Doctor doctor = doctordao.getDoctor(doctorId);
		session.setAttribute("doctor", doctor);
		model.setViewName("update-doctor");
		return model;
	}
	
	@PostMapping("/updateDoctor")
	public String updateDoctor(ModelAndView model, DoctorDAO doctordao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user-object");
		if(user.getRole().equalsIgnoreCase("admin")) {
			Doctor doctor = (Doctor) session.getAttribute("doctor");
			user = doctor.getUser();
		}
		System.out.println("sesion User: "+user);
		String department =  request.getParameter("department");
		System.out.println("department: " + department);
		
		String qualification =  request.getParameter("qualification");
		System.out.println("qualification: " + qualification);
		long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
		System.out.println("phoneNo: " + phoneNo);
		doctordao.updateDoctor(user,department,qualification,phoneNo);
		user = (User) session.getAttribute("user-object");
		if(user.getRole().equalsIgnoreCase("admin")) {
			session.removeAttribute("doctor");
			return "redirect:/home-admin";
		}
		return "redirect:/home-doctor";
	}
}
