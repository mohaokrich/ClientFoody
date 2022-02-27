package com.example.demo.service;

import javax.transaction.Transactional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UserRepository;



@Transactional
@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserRepository usuarioJPA;
	@Autowired
	RolRepository rolJPA;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario crearUsuario(Usuario u) {
		Rol rolUser = rolJPA.getById(1L);
		u.addRol(rolUser);
		u.setPasswdUsuario(bCryptPasswordEncoder.encode(u.getPasswdUsuario()));
		return usuarioJPA.save(u);
	}

	@Override
	public Usuario getUserByName(String nombre) {
		return usuarioJPA.findByNombreUsuario(nombre);
	}

	@Override
	public Usuario obtenerUsuarioById(long id) {
		return usuarioJPA.getById(id);
	}

}
