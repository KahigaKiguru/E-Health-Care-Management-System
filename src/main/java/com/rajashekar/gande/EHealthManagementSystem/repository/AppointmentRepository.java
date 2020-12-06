package com.rajashekar.gande.EHealthManagementSystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajashekar.gande.EHealthManagementSystem.model.Appointment;
import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;
import com.rajashekar.gande.EHealthManagementSystem.model.Patient;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{

	
	List<Appointment> findByDate(Date time);
	
	
	List<Appointment> findByDoctor(Doctor doctor);
	
	List<Appointment> findByPatient(Patient patient);
}
