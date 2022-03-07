package com.example.demo.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@ResponseBody
	@GetMapping("/comida/borrar/{id}")
	public void borrarComida(@PathVariable("id") Long id) {
		comidaJPA.borrarComida(id);
	}
	
	@ResponseBody
	@GetMapping("/comida/info/{id}")
	public ResponseEntity<Comida> getComidaById(@PathVariable("id") long id){
		Comida obtenerComida = comidaJPA.infoComida(id);
		ResponseEntity<Comida> com = new ResponseEntity<Comida>(obtenerComida, HttpStatus.OK);
		return com;
		
	}
	@ResponseBody
	@RequestMapping(method =  RequestMethod.PUT, value = "/editar/comida/{id}")
	public ResponseEntity<Comida> editarComida(@RequestBody Comida comida, @PathVariable("id") long id){
		Comida editarComida = comidaJPA.editarComida(comida, id);
		ResponseEntity<Comida> resp = new ResponseEntity<Comida>(editarComida, HttpStatus.OK);
		return resp;
	}
}
