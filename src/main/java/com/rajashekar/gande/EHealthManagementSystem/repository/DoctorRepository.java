package com.rajashekar.gande.EHealthManagementSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajashekar.gande.EHealthManagementSystem.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
	
	Doctor findByEmail(String email);
//	Doctor findByType(String type);
	List<Doctor> findByType(String type);
	List<Doctor> FindByAvailable(String available);
}
