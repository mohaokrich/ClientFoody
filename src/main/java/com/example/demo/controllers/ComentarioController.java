package com.example.demo.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Comida;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ComidaRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ComentarioService;


@Controller
public class ComentarioController {
	@Autowired
	ComentarioService comentarioJPA;
	@Autowired
	ComidaRepository comidaJPA;
	@Autowired
	UserRepository usuarioJPA;
	
	
	@GetMapping("/crearComentario/")
	public String crearComentario(HttpSession session) {
		Comida cs = comidaJPA.findById(1L).orElse(null);
		Usuario u = usuarioJPA.findById(1L).orElse(null);
		Comentario c = new Comentario("HOLAAA", new Date(), cs, u);
		comentarioJPA.guardarComentario(c);
		
		return "index";
	}
}
