package com.fernando.oliveira.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	
	@GetMapping("/aptoguaruja")
	public String init(Model model) {
		
		return "index";
	}

}
