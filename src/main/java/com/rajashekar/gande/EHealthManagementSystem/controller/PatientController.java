package com.rajashekar.gande.EHealthManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/patient")
public class PatientController {

	static List<String> patient_types = null;

	static {
		patient_types = new ArrayList<String>();
		patient_types.add("Adult");
		patient_types.add("Child");

	}

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AppointmentService appointmentService;

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

//	register, log in, update, patient
	@GetMapping("/registerPage")
	public String registerPage() {
		return "patient_register";
	}
	@PostMapping("/register")
	public String registerPatient(@ModelAttribute("patient") Patient patient) {
		patientService.createPatient(patient);
		return "redirect:/registerPage?registration_successful";
	}
	@GetMapping("/login")
	public String login() {
		return "patient_login";
	}
//	get available doctors by patient type
	@GetMapping("/availableDoctors")
	public String getAvailableDoctors(@RequestParam("patient_id") int patient_id, Model model) {

		Patient patient = patientService.getPatientById(patient_id);

		model.addAttribute("available_doctors", doctorService.getDoctorsByPatientType(patient.getType()));

		return "doctor_list";
		
	}

//	create, cancel, update , get appointments
	@GetMapping("/appointmentsPage")
	public String showAppointmentsPage(@RequestParam("patient_id") int patient_id, Model model) {
		Patient patient = patientService.getPatientById(patient_id);

		model.addAttribute("appointments", patient.getAppointments());

		return "appointments_page";
	}

	@GetMapping("/createAppointmentPage")
	public void showCreateAppointment(
			@RequestParam("patient_id") int patient_id,
			@RequestParam("doctor_id") int doctor_id,
			Model model) {
		Patient patient = patientService.getPatientById(patient_id);
		
		Doctor doctor = doctorService.getDoctorById(doctor_id);

		model.addAttribute("doctor", doctor);
		model.addAttribute("patient", patient);

	}

	@PostMapping("/createAppointment")
	public String createAppointment(
			@RequestParam("patient_id") int patient_id,
			@RequestParam("doctor_id") int doctor_id,
			@ModelAttribute("appointment") Appointment appointment) {

		Patient patient = patientService.getPatientById(patient_id);
		Doctor doctor = doctorService.getDoctorById(doctor_id);

		appointment.setDoctor(doctor);
		appointment.setPatient(patient);

		Appointment newAppointment = appointmentService.createAppointment(appointment);
		
		patient.getAppointments().add(newAppointment);
		doctor.getAppointments().add(newAppointment);
		
		
		patientService.updatePatient(patient);
		doctorService.updateDoctor(doctor);
		
		return "redirect:/appointmentPage?appointment_created";
	}
	@PostMapping("/updateAppointment")
	public String updateAppointment(
			@RequestParam("appointment_id") int appointment_id,
			@ModelAttribute("appointment") Appointment app) {
		Appointment appointment = appointmentService.getAppointmentById(appointment_id);
		
		appointment.setTime(app.getTime());
		
		appointmentService.updateAppointment(appointment);
		
		return "redirect:/appointmentsPage?appointment_updated";
	}
	
	@GetMapping("/cancelAppointment")
	public String deleteAppointment(@RequestParam("appointment_id") int appointment_id) {
		appointmentService.deleteAppointment(appointment_id);
		return "redirect:/appointmentsPage?appointment_cancelled";
	}
	
//	get prescriptions
	@GetMapping("/prescriptionsPage")
	public void showPrescriptionsPage(
			@RequestParam("patient_id") int patient_id,
			Model model) {
		
		Patient patient = patientService.getPatientById(patient_id);
		model.addAttribute("patient", patient);
		model.addAttribute("prescriptions", patient.getPrescriptions());
	}

}
