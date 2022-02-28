package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UserService;

@Controller
public class userController {
	
	@Autowired
	UserService usuarioService;
	
	//GET METHODS
	@GetMapping("/login")
	public String login() {
		return "formLogin";
	}

	@GetMapping("/signup")
	public String singUp() {
		return "formRegistro";
	}
	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String getLogOut(HttpSession session) {
//		session.removeAttribute("usuario");
//		session.invalidate();
//		return "redirect:/index";
//	}
	
	@RequestMapping(value = "/user/userInfo/{id}", method = RequestMethod.GET)
	public String getUsuario(@PathVariable long id, Model model) {
		Usuario usuario = usuarioService.obtenerUsuarioById(id);
        model.addAttribute("usuario", usuario);
		return "/user/userInfo";
	}
	
	//POST METHODS
	@PostMapping("/signup")
	public String crearUsuario(Usuario usuario) {
		usuarioService.crearUsuario(usuario);
		return "redirect:/index";
	}
	
	

}
