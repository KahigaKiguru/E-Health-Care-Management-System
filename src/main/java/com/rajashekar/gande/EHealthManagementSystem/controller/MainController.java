package com.rajashekar.gande.EHealthManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String homePage() {
		return "redirect:/patient/login";
	}
}
