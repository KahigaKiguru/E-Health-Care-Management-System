package com.rajashekar.gande.EHealthManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String registerPage() {
		return "patient_register";
	}

	public String registerPatient() {
		return "patient_login";
	}

	public String login() {
		return "patient_login";
	}

//	get available doctors by patient type
	public String getAvailableDoctors(int patient_id, Model model) {
		
		Patient patient = patientService.getPatientById(patient_id);		
		
		List<Doctor> availableDoctors = new ArrayList<Doctor>();
		
		if(patient.getType().equals("Child")) {
			model.addAttribute("available_doctors", availableDoctors.addAll(doctorService.getDoctorsByType("pediatrician")));	
		}else if(patient.getType().equals("Adult")){
			
			model.addAttribute("available_doctors", availableDoctors.addAll(doctorService.getDoctorsByType("General Practitioner")));
			
		}

	return "doctor_list";
}
//	create, cancel, update , get appointments
	
	public String showAppointmentsPage(int patient_id, Model model) {
		Patient patient = patientService.getPatientById(patient_id);
		
		model.addAttribute("appointments", patient.getAppointments());
		
		return "appointments_page";
	}
	
	public String createAppointment(int patient_id, int doctor_id, @ModelAttribute("appointment") Appointment appointment) {
		Patient patient = patientService.getPatientById(patient_id);
		Doctor doctor = doctorService.getDoctorById(doctor_id);
		
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		
		appointmentService.createAppointment(appointment);
		
		return "";
	}
	
	public String updateAppointment(int appointment_id, @ModelAttribute("appointment") Appointment app) {
		Appointment appointment = appointmentService.getAppointmentById(appointment_id);
		
		app.setTime(app.getTime());
		
		return "";
	}
	
	public String deleteAppointment(int appointment_id) {
		appointmentService.deleteAppointment(appointment_id);
		return "";
	}
//	get prescriptions
//	get, buy, drugs

}
