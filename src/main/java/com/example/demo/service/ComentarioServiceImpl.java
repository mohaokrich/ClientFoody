package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Comida;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.repository.ComidaRepository;
@Service
@Transactional		
public class ComentarioServiceImpl implements ComentarioService{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ComentarioRepository comentarioJPA;

	@Override
	public Comentario guardarComentario(Comentario comentario) {
		
		return comentarioJPA.save(comentario);
	}
	



}
