package com.example.demo.service;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<Comida> mostrarAllComidas() {
		return comidaJPA.findAll();
	}

	@Override
	public void borrarComida(long idComida) {
		comidaJPA.deleteById(idComida);
	}

	@Override
	public Comida infoComida(long idComida) {
		Comida buscarPorId = comidaJPA.findById(idComida).orElse(null);
		if(buscarPorId!=null) {
			return buscarPorId;
		}
		return null;
	}

	@Override
	public Comida editarComida(Comida c, long idComida) {
		Comida encontrarporId =  comidaJPA.findById(idComida).orElse(null);
		if (encontrarporId != null) {

			encontrarporId.setPais(c.getPais());

			Comida salvarComida = comidaJPA.save(encontrarporId);

			return salvarComida;
		}
		return null;
	}
	
}
