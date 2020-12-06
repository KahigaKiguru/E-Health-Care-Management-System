package com.rajashekar.gande.EHealthManagementSystem.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;
import com.rajashekar.gande.EHealthManagementSystem.model.Role;
import com.rajashekar.gande.EHealthManagementSystem.repository.DoctorRepository;

@Service
public class DoctorService implements UserDetailsService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

//	create doctor
	public Doctor createDoctor(Doctor doctor) {
		doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
		doctor.setRoles(Arrays.asList(new Role("ROLE_DOCTOR")));
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

//	get doctor by type
	public List<Doctor> getDoctorsByPatientType(String type) {

		List<Doctor> doctors = new ArrayList<Doctor>();

		if (type.equals("Child")) {

			for (Doctor doctor : doctorRepository.findByType("pediatrician")) {
				if (doctor.getAvailable().equals("AVAILABLE")) {
					doctors.add(doctor);
				}
			}
		} else if (type.equals("Adult")) {

			for (Doctor doctor : doctorRepository.findByType("General Practitioner")) {
				if (doctor.getAvailable().equals("AVAILABLE")) {
					doctors.add(doctor);
				}
			}
		}

		return doctors;
	}
//	get all doctors

	public Iterable<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Doctor doctor = doctorRepository.findByEmail(username);

		if (doctor != null) {
			return doctor;
		} else {
			throw new UsernameNotFoundException("user not found");
		}
	}
}
