package com.rajashekar.gande.EHealthManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajashekar.gande.EHealthManagementSystem.model.Drug;
import com.rajashekar.gande.EHealthManagementSystem.repository.DrugRepository;

@Service
public class DrugService {

	@Autowired
	private DrugRepository drugRepository;
	
//	create a drug
	public Drug createDrug(Drug drug) {
		return drugRepository.save(drug);
	}
//	update a drug
	public void updateDrug(Drug drug) {
		drugRepository.save(drug);
	}
//	get drug by id
	public Drug getDrugById(int id) {
		return drugRepository.findById(id).get();
	}
//	get all drugs
	public Iterable<Drug> getAllDrugs() {
		return drugRepository.findAll();
	}
}
