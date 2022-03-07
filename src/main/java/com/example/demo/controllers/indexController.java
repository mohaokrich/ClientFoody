package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Comida;
import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ComidaService;
import com.example.demo.service.UserService;

@Controller
public class indexController {

	@Autowired
	ComidaService comidaService;
	
	@GetMapping("/index")
	public String paginaInicio() {
		return "/index";
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/comidas")
	public List<Comida> obtenerTodas() {
		return comidaService.mostrarAllComidas();
	}
}
