package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Comida;
import com.example.demo.repository.ComidaRepository;
import com.example.demo.repository.UserRepository;
@Controller
public class comidaController {
	
	@Autowired
	private ComidaRepository comidaJPA;

	@ResponseBody
	@PostMapping("/crearComida")
	public ResponseEntity postCrearOferta(@RequestBody Map<String, String> json) {

		Comida comida = comidaJPA.save(new Comida(json.get("nombre"), json.get("pais"),
		json.get("hiperenlace"), json.get("descripcion")));
		ResponseEntity<Object> com = new ResponseEntity<Object>(comida, HttpStatus.OK);
		
		return com;
	}
}
