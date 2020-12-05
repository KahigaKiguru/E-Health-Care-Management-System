package com.rajashekar.gande.EHealthManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Pharmacy;
import com.rajashekar.gande.EHealthManagementSystem.repository.PharmacyRepository;

@Service
public class PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;
	
//	create, update, get all, get id
	
	public Pharmacy createPharmacy(Pharmacy pharmacy) {
		return pharmacyRepository.save(pharmacy);
	}
	
	public void updatePharmacy(Pharmacy pharmacy) {
		pharmacyRepository.save(pharmacy);
	}
	
	public Iterable<Pharmacy> getAllPharmacies(){
		return pharmacyRepository.findAll();
	}
	
	public Pharmacy getPharmacyById(int id) {
		return pharmacyRepository.findById(id).get();
	}
}
