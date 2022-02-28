package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Comida;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ComidaRepository;
import com.example.demo.repository.UserRepository;

public class comidaController {
	
	@Autowired
	private ComidaRepository comidaJPA;
	@Autowired
	private UserRepository userJPA;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/comida/crear")
	public ResponseEntity<Comida> obtenerDatosFormulario(@RequestBody Comida comida, Usuario u) {
		
		comida.setId_usuario(u.getIdUsuario());
		Date date = new Date();
		comida.setFecha(date);

		Comida crearComida = comidaJPA.save(comida);
		
		ResponseEntity<Comida> resp = new ResponseEntity<Comida>(crearComida, HttpStatus.OK);
		return resp;
    }
	
}
