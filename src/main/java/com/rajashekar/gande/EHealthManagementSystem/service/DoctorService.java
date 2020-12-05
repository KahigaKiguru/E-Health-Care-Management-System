package com.rajashekar.gande.EHealthManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;
import com.rajashekar.gande.EHealthManagementSystem.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//	create doctor
	public Doctor createDoctor(Doctor doctor) {
		doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
		return doctorRepository.save(doctor);
	}
//	update doctor
	public void updateDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}
//	get doctor by id
	public Doctor getDoctorById(int id) {
		return doctorRepository.findById(id).get();
	}
//	get doctor by email
	public Doctor getDoctorByEmail(String email) {
		return doctorRepository.findByEmail(email);
	}
//	get all doctors
	
	public Iterable<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
}
