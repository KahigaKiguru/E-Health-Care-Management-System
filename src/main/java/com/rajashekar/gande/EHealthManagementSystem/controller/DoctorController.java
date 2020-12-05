package com.rajashekar.gande.EHealthManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rajashekar.gande.EHealthManagementSystem.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	static List<String> doctor_types = null;
	static List<String> doctor_status = null;
	static {
		doctor_types = new ArrayList<String>();
		doctor_types.add("General Practitioner");
		doctor_types.add("pediatrician");
		doctor_status.add("AVAILABLE");
		doctor_status.add("UNAVAILABLE");
		
	}
	
	@ModelAttribute("doctor")
	@ModelAttribute("patient")
	@ModelAttribute("drug")
	@ModelAttribute("appointment")
	@ModelAttribute("pharmacy")
	@ModelAttribute("prescription")
}
