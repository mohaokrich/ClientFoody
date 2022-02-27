package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UserService;

@Controller
public class indexController {

	@Autowired
	UserService usuarioService;
	
	@GetMapping("/index")
	public String paginaInicio() {
		return "index";
	}

}
