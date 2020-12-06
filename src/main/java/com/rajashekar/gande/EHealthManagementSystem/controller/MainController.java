package com.rajashekar.gande.EHealthManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	public String  homePage() {
		return "index";
	}
}
