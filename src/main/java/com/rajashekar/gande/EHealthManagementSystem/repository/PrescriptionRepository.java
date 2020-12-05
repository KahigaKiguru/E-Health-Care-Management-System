package com.rajashekar.gande.EHealthManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajashekar.gande.EHealthManagementSystem.model.Prescription;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {

}
