package com.example.demo.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comida;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ComidaRepository;
import com.example.demo.repository.UserRepository;
@Service
@Transactional
public class ComidaServiceImpl implements ComidaService {
	@Autowired
	ComidaRepository comidaJPA;
	@Autowired
	UserRepository userJPA;

	@Override
	public Comida crearComida(Comida comida) {
		Usuario u = new Usuario(); 
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario authUser = userJPA.findByNombreUsuario(userDetails.getUsername());
		
		u = userJPA.findById(authUser.getIdUsuario()).orElse(u);
		
		u.addComida(comida);
		comida.addUsuario(u);

		return comidaJPA.save(comida);
	}

}
