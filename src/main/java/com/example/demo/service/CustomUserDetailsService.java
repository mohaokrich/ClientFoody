package com.example.demo.service;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UserRepository;


@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository usuarioJPA;
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Usuario usuario = usuarioJPA.findByNombreUsuario(nombre);
		
		Set<Rol> l = usuario.getRoles();
		HashSet<Rol> roles = new HashSet<>();

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Rol rol : usuario.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}
		return new org.springframework.security.core.userdetails.User(usuario.getNombreUsuario(), usuario.getPasswdUsuario(),
				grantedAuthorities);
	}

}