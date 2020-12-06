package com.rajashekar.gande.EHealthManagementSystem.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Patient;
import com.rajashekar.gande.EHealthManagementSystem.model.Role;
import com.rajashekar.gande.EHealthManagementSystem.repository.PatientRepository;

@Service
public class PatientService implements UserDetailsService{

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//	create patient
	public Patient createPatient(Patient patient) {
		
		patient.setPassword(passwordEncoder.encode(patient.getPassword()));
		patient.setRoles(Arrays.asList(new Role("ROLE_PATIENT")));
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
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Patient patient = patientRepository.findByEmail(username);
	if(patient != null) {
		return patient;
	}
	throw new UsernameNotFoundException("patient not found");
}
}
