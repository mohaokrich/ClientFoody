package com.example.demo.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
//		Date date = new Date();
//		Usuario u = new Usuario();
//		userJPA.findById(u.getIdUsuario());
//		
//		comida.addUsuario(u);
//		comida.setFecha(date);
//		
//		u.addComida(comida);
		
		return comidaJPA.save(comida);
		
	}

}
