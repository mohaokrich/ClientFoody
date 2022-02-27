package com.example.demo.service;

import com.example.demo.entity.Usuario;

public interface UserService {
	Usuario crearUsuario(Usuario u);
	Usuario getUserByName(String nombre);
	Usuario obtenerUsuarioById(long id);
}
