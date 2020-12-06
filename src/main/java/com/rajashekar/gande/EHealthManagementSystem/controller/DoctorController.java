package com.rajashekar.gande.EHealthManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rajashekar.gande.EHealthManagementSystem.model.Appointment;
import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;
import com.rajashekar.gande.EHealthManagementSystem.model.Drug;
import com.rajashekar.gande.EHealthManagementSystem.model.Patient;
import com.rajashekar.gande.EHealthManagementSystem.model.Pharmacy;
import com.rajashekar.gande.EHealthManagementSystem.model.Prescription;
import com.rajashekar.gande.EHealthManagementSystem.service.AppointmentService;
import com.rajashekar.gande.EHealthManagementSystem.service.DoctorService;
import com.rajashekar.gande.EHealthManagementSystem.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	static List<String> doctor_types = new ArrayList<String>();
	static List<String> doctor_status = new ArrayList<String>();
	static {
		doctor_types = new ArrayList<String>();
		doctor_types.add("General Practitioner");
		doctor_types.add("pediatrician");
		doctor_status.add("AVAILABLE");
		doctor_status.add("UNAVAILABLE");
		
	}
	
	@ModelAttribute("doctor")
	public Doctor doctorModel() {
		return new Doctor();
	}
	@ModelAttribute("patient")
	public Patient patientModel() {
		return new Patient();
	}
	@ModelAttribute("drug")
	public Drug drugModel() {
		return new Drug();
	}
	@ModelAttribute("appointment")
	public Appointment appointmentModel() {
		return new Appointment();
	}
	@ModelAttribute("pharmacy")
	public Pharmacy pharmacyModel() {
		return new Pharmacy();
	}
	@ModelAttribute("prescription")
	public Prescription prescriptionModel() {
		return new Prescription();
	}
	
//	register, log in, update, doctor
	@GetMapping("/registerPage")
	public String showDoctorRegistrationPage(Model model) {
		model.addAttribute("doctor_types", doctor_status);
		model.addAttribute("doctor_types", doctor_types);
		return "doctor_register";
	}
	@PostMapping("/register")
	public String registerDoctor(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.createDoctor(doctor);
		return "redirect:/registerPage?registration_successful";
	}
	@GetMapping("/login")
	public String login() {
		return "doctor_login";
	}
	@GetMapping("/updatePage")
	public String showUpdatePage(@RequestParam("doctor_id") int doctor_id, Model model) {
		model.addAttribute("doctor", doctorService.getDoctorById(doctor_id));
		return "doctor_update";
	}
	@PostMapping("/update")
	public String update(
			@RequestParam("doctor_id") int doctor_id,
			@ModelAttribute("Doctor") Doctor doc,
			Model model) {
		
		Doctor doctor = doctorService.getDoctorById(doctor_id);
		
		doctor.setName(doc.getName());
		doctor.setEmail(doc.getEmail());
		doctor.setAvailable(doc.getAvailable());
		doctor.setPassword(passwordEncoder.encode(doc.getPassword()));
		doctor.setType(doc.getType());
		
		doctorService.updateDoctor(doctor);
		
		model.addAttribute("doctor", doctor);
		
		return "redirect:/doctorPage?account_updated";
	}
//	show doctor page
	@GetMapping("/doctorPage")
	public String showDoctorPage(@RequestParam("doctor_id") int doctor_id, Model model) {
		Doctor doctor = doctorService.getDoctorById(doctor_id);
		model.addAttribute("doctor", doctor);
		return "doctor_page";
	}
// 	get patients
	public String patients(@RequestParam("doctor_id") int doctor_id, Model model) {
		Doctor doctor = doctorService.getDoctorById(doctor_id);
		model.addAttribute("patients", doctor.getPatients());
		
		return "doctor_patients";
	}
//	get, update, cancel appointments
	@GetMapping("/appointmentsPage")
	public String showAppointments(@RequestParam("doctor_id") int doctor_id, Model model) {
		Doctor doctor = doctorService.getDoctorById(doctor_id);
		
		model.addAttribute("appointments", doctor.getAppointments());
		
		return "doctor_appointments";
	}
	
	@PostMapping("/updateAppointment")
	public String updateAppointment(
			@RequestParam("appointment_id") int appointment_id, 
			@ModelAttribute("appointment") Appointment app) {
		
		Appointment appointment = appointmentService.getAppointmentById(appointment_id);
		
		appointment.setTime(app.getTime());
		appointment.setReport(app.getReport());
		
		appointmentService.updateAppointment(appointment);
		
		return "redirect:/appointmentsPage?appointment_updated";
	}
	@GetMapping("/clearAppointment")
	public String clearAppointment(
			@RequestParam("appointment_id") int appointment_id,
			@RequestParam("patient_id") int patient_id,
			@ModelAttribute("prescription") Prescription prescription, 
			@RequestParam("report") String report) {
		
		Appointment appointment = appointmentService.getAppointmentById(appointment_id);
		
		Doctor doctor = appointment.getDoctor();
		
		Patient patient = patientService.getPatientById(patient_id);
		
		patient.getPrescriptions().add(prescription);
		
		doctor.getAppointments().remove(appointment);

		appointment.setReport(report);
		
		doctorService.updateDoctor(doctor);
		
		patientService.updatePatient(patient);
		
		appointmentService.updateAppointment(appointment);
		
		return "redirect:/appointmentsPage?appointment_cleared";
	}
}
