package com.esd.MedTab.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.esd.MedTab.pojo.*;
import com.esd.MedTab.dao.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AppointmentController {

	@GetMapping("/bookAppointment")
	public ModelAndView bookAppointment(ModelAndView model, DoctorDAO doctordao, HttpServletRequest request) {
		List<Doctor> doctors = doctordao.getAllDoctors();
//		System.out.println("AllDoctors: "+doctors);		
		request.setAttribute("allDoctors",doctors);
//		model.addObject("allDoctors",doctors);
		model.setViewName("new-appointment");
		return model;
	}
	
	@PostMapping("/bookAppointment")
	public String saveAppointment(DoctorDAO doctordao, PatientDAO patientdao, AppointmentDAO appointmentdao, Appointment newAppointment, HttpServletRequest request) {
		System.out.println("in saveAppointment function");
		String doctorID = (String) request.getParameter("selectDoctor"); 
		String date = (String) request.getParameter("selectDate"); 
		String description = (String) request.getParameter("appointmentDescription"); 
		
		System.out.println("doctorID: "+doctorID);
		Doctor doctor = doctordao.getDoctor(doctorID);
		System.out.println("doctor: "+doctor);
		User user = (User) request.getSession().getAttribute("user-object");
		System.out.println("user: "+user);
		Patient patient = patientdao.getPatient(user);
		System.out.println("patient: "+patient);
		
		newAppointment.setPatient(patient);
		newAppointment.setDoctor(doctor);
		newAppointment.setAppointmentDate(date);
		newAppointment.setAppointmentDescription(description);
		newAppointment.setRegisterDate(LocalDateTime.now());
		appointmentdao.bookAppointment(newAppointment);
		return "redirect:http://localhost:8081/MedTab1/home-patient";
	}
	
	public LocalDateTime getMidnight() {
	    LocalTime midnight = LocalTime.MIDNIGHT;
	    LocalDate today = LocalDate.now(ZoneId.systemDefault());
	    LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
	    return todayMidnight;
	  }
	
	public boolean containsDate(final List<Appointment> list, String date){
	    return list.stream().filter(o -> o.getAppointmentDate().toString().equals(date)).findFirst().isPresent();
	}
	
	@GetMapping("/getDoctors")
	public @ResponseBody List<Doctor> getDoctors(HttpServletRequest request) {
		System.out.println("In getDoctors functions");
		AppointmentDAO appdao = new AppointmentDAO();
		List<Doctor> doctors = appdao.getDoctors();
		System.out.println(doctors);
		return doctors;
//		Date nearestHour = DateUtils.round( now, Calendar.HOUR );
	}
	
	@GetMapping("/datesAvailable")
	public @ResponseBody List<String> datesAvailable(AppointmentDAO appdao, HttpServletRequest request) {
//		System.out.println("In datesAvailable functions");
		List<String> availableDates = new ArrayList<String>();
		String doctorId = (String) request.getParameter("doctorSelected");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = getMidnight();
		date = date.plusHours(24+8);
		String strDate = date.format(dateTimeFormatter);
		List<Appointment> appointments = appdao.getBookdedtime(doctorId,strDate);
		for(int i=1;i<=7;i++) {
			for(int j=0;j<24;j++){
				date = date.plusMinutes(30);
				strDate = date.format(dateTimeFormatter);
				if(!containsDate(appointments,strDate)) {
					availableDates.add(strDate);
				}
			}
			date = date.plusHours(12);
		}
//		System.out.println("availableDates: "+availableDates);
		return availableDates;
	}
	
	@GetMapping("/updateAppointment")
	public ModelAndView updateAppointment (ModelAndView model, AppointmentDAO appointmentdao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user-object");
		if (user==null) {
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
		}
		if(!(user.getRole().equalsIgnoreCase("Doctor"))){
			model.setViewName("invalid");
			return model;
		}
		String appointmentID = request.getParameter("appointmentid");
		Appointment appointment = appointmentdao.getAppointment(appointmentID);
		session.setAttribute("appointment",appointment);
		model.setViewName("update-appointment");
		return model;
	}
	
	@PostMapping("/updateAppointment")
	public String updateAppointmentPost (ModelAndView model, AppointmentDAO appointmentdao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String doctorComments = (String) request.getParameter("doctorComments"); 
		String prescription = (String) request.getParameter("prescription"); 
		Appointment appointment = (Appointment) session.getAttribute("appointment");
		if(doctorComments!=null) {
			appointment.setDoctorComment(doctorComments);
		}
		if(prescription!=null) {
			appointment.setPrescription(prescription);
		}
		appointmentdao.updateAppointment(appointment);
		session.removeAttribute("appointment");
		return "redirect:/getDoctorAppointments";
	}
	
}
