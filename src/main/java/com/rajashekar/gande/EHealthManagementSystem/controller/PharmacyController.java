package com.rajashekar.gande.EHealthManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rajashekar.gande.EHealthManagementSystem.model.Drug;
import com.rajashekar.gande.EHealthManagementSystem.model.Patient;
import com.rajashekar.gande.EHealthManagementSystem.model.Pharmacy;
import com.rajashekar.gande.EHealthManagementSystem.service.DrugService;
import com.rajashekar.gande.EHealthManagementSystem.service.PatientService;
import com.rajashekar.gande.EHealthManagementSystem.service.PharmacyService;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private PatientService patientService;
	
//	create, update, delete, get , get all pharmacies
	
	@GetMapping("/pharmacy_page")
	public String showPharmacyPage() {
		return "pharmacy_page";
	}
	
	@PostMapping("/create")
	public void createPharmacy(Pharmacy pharmacy) {
		 pharmacyService.createPharmacy(pharmacy);
	}
	@PostMapping("/update")
	public void updatePharmacy(Pharmacy pharmacy) {
		pharmacyService.updatePharmacy(pharmacy);
	}
	
	@GetMapping("/delete")
	public void deletePharmacy(int pharmacy_id) {
		pharmacyService.deletePharmacy(pharmacy_id);
	}
	
//	add, update, get, delete drugs
	@PostMapping("/addDrug")
	public String addDrug(int pharmacy_id, Drug drug) {
		Pharmacy pharmacy = pharmacyService.getPharmacyById(pharmacy_id);
		
		drug.setPharmacy(pharmacy);
		pharmacy.getDrugs().add(drug);
		
		pharmacyService.updatePharmacy(pharmacy);
		drugService.updateDrug(drug);
		
		return "redirect:/pharmacyPage?drug_added";
	}
	
	@PostMapping("/updateDrug")
	public String updateDrug(int drug_id, Drug pdrug) {
		Drug drug = drugService.getDrugById(drug_id);
		
		drug.setName(pdrug.getName());
		drug.setDosage(pdrug.getDosage());
		drug.setQuantity(pdrug.getQuantity());
		drug.setPrice(pdrug.getPrice());
		drug.setDescription(pdrug.getDescription());
		
		drugService.updateDrug(drug);
		
		return "redirect:/pharmacyPage?drug_updated";
	}

	@GetMapping("/buyDrug")
	public String buyDrugs(
			@RequestParam("patient_id") int patient_id,
			@RequestParam("drug_id") int drug_id,
			@RequestParam("quantity") int quantity) {
		
		Patient patient = patientService.getPatientById(patient_id);
		Drug drug = drugService.getDrugById(drug_id);
		
		drug.setQuantity(drug.getQuantity() - quantity);
		
		patient.getDrugs().add(drug);
		
		patientService.updatePatient(patient);
		drugService.updateDrug(drug);
		
		return "redirect:/pharmacyPage?purchase_successful";
	}
}
