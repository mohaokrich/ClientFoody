package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comida;


public interface ComidaService {
	Comida crearComida(Comida comida);
	List<Comida> mostrarAllComidas();
	void borrarComida(long idComida);
	Comida infoComida(long idComida);
	Comida editarComida(Comida c,long idComida);
}
