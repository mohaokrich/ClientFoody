package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

	@GetMapping("/index")
	public String paginaInicio() {
		
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		
		return "formLogin";
	}
	@GetMapping("/singUp")
	public String singUp() {
		
		return "formRegistro";
	}
}
