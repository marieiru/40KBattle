package com.example.KBattle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SlgmController {

	@GetMapping("/")
	public String menu() {
		
		return "menu";
	}
	


	
}
