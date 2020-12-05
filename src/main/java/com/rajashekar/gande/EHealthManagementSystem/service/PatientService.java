package com.rajashekar.gande.EHealthManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Patient;
import com.rajashekar.gande.EHealthManagementSystem.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//	create patient
	public Patient createPatient(Patient patient) {
		
		patient.setPassword(passwordEncoder.encode(patient.getPassword()));
		
		return patientRepository.save(patient);
		
	}
//	update patient
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}
//	get patient by id
	public Patient getPatientById(int id) {
		return patientRepository.findById(id).get();
	}
//	get patient by email
	public Patient getPatientByEmail(String email) {
		return patientRepository.findByEmail(email);
	}
}
