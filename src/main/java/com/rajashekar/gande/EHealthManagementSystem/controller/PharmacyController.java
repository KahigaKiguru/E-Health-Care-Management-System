package com.rajashekar.gande.EHealthManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	static List<String> pharmacy_categories = new ArrayList<String>();

	static {
		pharmacy_categories.add("Adult Pharmacy");
		pharmacy_categories.add("Children's Pharmacy");
		pharmacy_categories.add("General Pharmacy");

	}

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private DrugService drugService;

	@Autowired
	private PatientService patientService;

	@ModelAttribute("pharmacy")
	public Pharmacy pharmacyBinding() {
		return new Pharmacy();
	}
//	create, update, delete, get , get all pharmacies
	
	  @GetMapping("/pharmacyList") public String showPharmacyPage(Model model) {
	  
		  model.addAttribute("pharmacy_categories", pharmacy_categories);
		  model.addAttribute("pharmacies", pharmacyService.getAllPharmacies());
		  return "pharmacy_list"; 
	  
	  }

	@GetMapping("/pharmacyPage")
	public String showPharmacyList(
			@RequestParam("pharmacy_id") int pharmacy_id, Model model) {
		Pharmacy pharmacy = pharmacyService.getPharmacyById(pharmacy_id);
		model.addAttribute("pharmacy", pharmacy);
		model.addAttribute("available_drugs", pharmacy.getDrugs());
		model.addAttribute("pharmacy_id", pharmacy_id);
		return "pharmacy_page";
	}
	@GetMapping("/createPage")
	public String  showcreatePharmacyPage(Model model) {
		model.addAttribute("categories", pharmacy_categories);
		return "pharmacy_add";
		
	}
	@PostMapping("/create")
	public String createPharmacy(@ModelAttribute("pharmacy") Pharmacy pharmacy) {
		pharmacyService.createPharmacy(pharmacy);
		return "redirect:/pharmacy/pharmacyList?pharmacy_added";
	}

	@PostMapping("/update")
	public void updatePharmacy(@RequestParam("pharmacy_id") int pharmacy_id,
			@ModelAttribute("pharmacy") Pharmacy pharmacy) {
		pharmacyService.updatePharmacy(pharmacy);
	}

	@GetMapping("/delete")
	public void deletePharmacy(int pharmacy_id) {
		pharmacyService.deletePharmacy(pharmacy_id);
	}

//	add, update, get, delete drugs
	@GetMapping("/drugsPage")
	public String showDrugsList(@RequestParam("patient_id") int patient_id, Model model) {
		model.addAttribute("drugs", drugService.getAllDrugs());
		model.addAttribute("patient", patientService.getPatientById(patient_id));
		return "drug_list";
	}

	@GetMapping("/addDrugPage")
	public String addDrugPage(@RequestParam("pharmacy_id") int pharmacy_id, Model model) {
		
		Pharmacy pharmacy = pharmacyService.getPharmacyById(pharmacy_id);
		model.addAttribute("pharmacy", pharmacy);
		return "drug_add";
	}
	@PostMapping("/addDrug")
	public String addDrug(
			@RequestParam("pharmacy_id") int pharmacy_id,
			@ModelAttribute("drug") Drug drug) {
		Pharmacy pharmacy = pharmacyService.getPharmacyById(pharmacy_id);

		drug.setPharmacy(pharmacy);
		pharmacy.getDrugs().add(drug);

		pharmacyService.updatePharmacy(pharmacy);
		drugService.updateDrug(drug);

		return "redirect:/pharmacyList?drug_added";
	}

	@GetMapping("/updateDrugPage")
	public String updateDrugPage(@RequestParam("pharmacy_id") int pharmacy_id, @RequestParam("drug_id") int drug_id, Model model) {
		
		model.addAttribute("drug", drugService.getDrugById(drug_id));
		model.addAttribute("pharmacy", pharmacyService.getPharmacyById(pharmacy_id));
		return "drug_update";
	}
	@PostMapping("/updateDrug")
	public String updateDrug(@RequestParam("drug_id") int drug_id, @ModelAttribute("drug") Drug pdrug) {
		Drug drug = drugService.getDrugById(drug_id);

		drug.setName(pdrug.getName());
		drug.setDosage(pdrug.getDosage());
		drug.setQuantity(pdrug.getQuantity());
		drug.setPrice(pdrug.getPrice());
		drug.setDescription(pdrug.getDescription());

		drugService.updateDrug(drug);

		return "redirect:/pharmacy/pharmacyList?drug_updated";
	}

	@GetMapping("/deleteDrug")
	public String deleteDrug(@RequestParam("drug_id") int drug_id) {
		drugService.deleteDrug(drug_id);
		return "redirect:/pharmacy/pharmacyList?drug_deleted";
	}
	@GetMapping("/buyDrugPage")
	public String buyDrugPage(
			@RequestParam("patient_id") int patient_id,
			@RequestParam("drug_id") int drug_id, Model model) {
		model.addAttribute("patient", patientService.getPatientById(patient_id));
		model.addAttribute("drug", drugService.getDrugById(drug_id));
		return "drug_buy";
	}
	@PostMapping("/buyDrug")
	public String buyDrug(
			@RequestParam("patient_id") int patient_id,
			@RequestParam("drug_id") int drug_id,
			@RequestParam("quantity") int quantity) {

		Patient patient = patientService.getPatientById(patient_id);
		
		Drug drug = drugService.getDrugById(drug_id);

		drug.setQuantity(drug.getQuantity() - quantity);

		patient.getDrugs().add(drug);

		patientService.updatePatient(patient);
		drugService.updateDrug(drug);

		return "redirect:/patient/patientPage?purchase_successful";
	}
}
