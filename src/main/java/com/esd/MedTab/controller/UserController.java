package com.esd.MedTab.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.esd.MedTab.dao.AppointmentDAO;
import com.esd.MedTab.dao.DoctorDAO;
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
public class UserController {
	
	@GetMapping("/signup")
	public ModelAndView signUpForm(ModelAndView model, HttpServletRequest request) {
		String type = (String) request.getParameter("userType");
		request.setAttribute("userType",type);
		model.addObject("user", new User());
		model.setViewName("signup");
		return model;
	}
	
	@GetMapping("/signup-error")
	public ModelAndView signUpErrorForm(ModelAndView model, HttpServletRequest request) {
		String type = (String) request.getParameter("userType");
		request.setAttribute("userType",type);
		request.setAttribute("err_msg","Username already exist!!");
		model.addObject("user", new User());
		model.setViewName("signup");
		return model;
	}
	
	@PostMapping("/signup")
	public String addPatient(ModelAndView model, @ModelAttribute("user") User user, UserDAO userdao, HttpServletRequest request) {
		String type = (String) request.getParameter("userType");
		User tempUser = userdao.findUser(user.getEmail());
		if(tempUser!= null) {
			return "redirect:/signup-error";
		}
		if("Patient".equalsIgnoreCase(type)){
			user.setRole("Patient");
			user = userdao.addUser(user);
			Patient patient = new Patient();
			patient.setUser(user);
			patient = userdao.addPatient(patient);
		}
		else if("Doctor".equalsIgnoreCase(type)) {
			user.setRole("Doctor");
			user = userdao.addUser(user);
			Doctor doctor = new Doctor();
			doctor.setUser(user);
			doctor = userdao.addDoctor(doctor);
		}
		else if("Admin".equalsIgnoreCase(type)) {
			user.setRole("Admin");
			user = userdao.addUser(user);
			Admin admin = new Admin();
			admin.setUser(user);
			admin = userdao.addAdmin(admin);
		}
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user-object");
		if(user!=null) {
			if(user.getRole().equalsIgnoreCase("admin")) {
				session.removeAttribute("patient");
				return "redirect:/home-admin";
			}
		}
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView model) {
		model.addObject("user", new User());
		model.setViewName("login");
		return model;
	}
	
	@GetMapping("/login-error")
	public ModelAndView loginError(ModelAndView model, HttpServletRequest request) {
		model.addObject("user", new User());
		request.setAttribute("err_msg","Invalid Credentials!!");
		model.setViewName("login");
		return model;
	}
	
	@GetMapping("/logout")
	public String logout (ModelAndView model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user-object");
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public ModelAndView welcomePage(ModelAndView model) {
		model.setViewName("welcome");
		return model;
	}
	
	@GetMapping("/invalid")
	public ModelAndView invalidPage(ModelAndView model) {
		model.setViewName("invalid");
		return model;
	}
	
	@PostMapping("/login")
	public String login(ModelAndView model, @ModelAttribute("user") User user, UserDAO userdao, HttpServletRequest request) {
		System.out.println("before");
		User tempUser = userdao.authenticateUser(user.getEmail(),user.getPassword());
		System.out.println(tempUser);
		if(tempUser== null) {
			return "redirect:/login-error";
		}
		request.getSession().setAttribute("user-object", tempUser);
		if ("Patient".equalsIgnoreCase(tempUser.getRole())) {
			return "redirect:/home-patient";
		}
		else if ("Admin".equalsIgnoreCase(tempUser.getRole())) {
			return "redirect:/home-admin";
		}
		else if ("Doctor".equalsIgnoreCase(tempUser.getRole())) {
			return "redirect:/home-doctor";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/home-patient")
	public ModelAndView homePatient(ModelAndView model, AppointmentDAO appointmentdao, PatientDAO patientdao, HttpServletRequest request) {
//		model.addObject("user", new User());
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
//		System.out.println("Appointments: " + patient);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.now();
		String strDate = date.format(dateTimeFormatter);
		List<Appointment> appointments = appointmentdao.getPatientAppointments(patient, strDate);
		request.setAttribute("upAppointments", appointments);
		model.setViewName("home-patient");
		return model;
	}
	@GetMapping("/home-admin")
	public ModelAndView homeAdmin(ModelAndView model, HttpServletRequest request) {
//		model.addObject("user", new User());
		User user = (User) request.getSession().getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Admin"))){
			model.setViewName("invalid");
			return model;
		}
		model.setViewName("home-admin");
		return model;
	}
	@GetMapping("/home-doctor")
	public ModelAndView homeDoctor(ModelAndView model, AppointmentDAO appointmentdao, DoctorDAO doctordao, HttpServletRequest request) {
//		model.addObject("user", new User());
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
		model.setViewName("home-doctor");
		Doctor doctor = doctordao.getDoctor(user);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.now();
		String strDate = date.format(dateTimeFormatter);
		List<Appointment> appointments = appointmentdao.getDoctorAppointments(doctor, strDate);
		request.setAttribute("upAppointments", appointments);
		System.out.println("Upcoming Appointments: "+ appointments);
		return model;
	}

}
