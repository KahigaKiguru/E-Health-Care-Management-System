package com.rajashekar.gande.EHealthManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
	
	Doctor findByEmail(String email);
}
