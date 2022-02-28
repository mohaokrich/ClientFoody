package com.example.demo.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.Comida;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ComidaService;

@Controller
public class comidaController {
	@Autowired
	private ComidaService comidaJPA;


	@ResponseBody
	@PostMapping("/crearComida")
	public ResponseEntity postCrearOferta(@RequestBody Map<String, String> json, Usuario u, HttpSession session) {
		//u = userJPA.findById(u.getIdUsuario()).orElse(null);
	
		Comida comida = comidaJPA.crearComida(new Comida(json.get("nombre"), json.get("pais"),
		json.get("hiperenlace"), json.get("descripcion")));
		
		ResponseEntity<Object> com = new ResponseEntity<Object>(comida, HttpStatus.OK);
		
		return com;
	}
}
