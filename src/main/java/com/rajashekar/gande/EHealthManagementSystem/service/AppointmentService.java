package com.rajashekar.gande.EHealthManagementSystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Appointment;
import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;
import com.rajashekar.gande.EHealthManagementSystem.model.Patient;
import com.rajashekar.gande.EHealthManagementSystem.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
//	create, update, delete, get all, get id, get date, get doctor, get patient
	
	public Appointment createAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	public void updateAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}
	
	public void deleteAppointment(int id) {
		appointmentRepository.deleteById(id);
	}
	
	public Appointment getAppointmentById(int id) {
		return appointmentRepository.findById(id).get();
	}
	
	public Iterable<Appointment> getAllAppointments(){
		return appointmentRepository.findAll();
	}
	
	public List<Appointment> getAppointmentsByTime(Date time){
		return appointmentRepository.findByDate(time);
	}
	
	public List<Appointment> getAppointmentsByPatient(Patient patient){
		return appointmentRepository.findByPatient(patient);
	}
	
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor){
		return appointmentRepository.findByDoctor(doctor);
	}
}
