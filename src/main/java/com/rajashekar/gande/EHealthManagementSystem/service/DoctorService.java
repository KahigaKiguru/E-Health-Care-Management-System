package com.rajashekar.gande.EHealthManagementSystem.service;

import java.util.List;

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
//	get available doctors
	public List<Doctor> getAvailableDoctors(){
		return doctorRepository.FindByAvailable("AVAILABLE");
	}
//	get doctor by type
	public List<Doctor> getDoctorsByType(String type){
		return doctorRepository.findByType(type);
	}
//	get all doctors
	
	public Iterable<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
}
