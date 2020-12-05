package com.rajashekar.gande.EHealthManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajashekar.gande.EHealthManagementSystem.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer>{

	Patient findByEmail(String email);
	
}
