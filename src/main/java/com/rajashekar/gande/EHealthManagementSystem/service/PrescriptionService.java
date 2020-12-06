package com.rajashekar.gande.EHealthManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Prescription;
import com.rajashekar.gande.EHealthManagementSystem.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	public Prescription createPrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	
	
}
