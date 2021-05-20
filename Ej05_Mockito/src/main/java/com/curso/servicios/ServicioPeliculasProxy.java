package com.curso.servicios;

import java.util.List;

import com.curso.modelo.entidad.Pelicula;

public interface ServicioPeliculasProxy {

	void insertar(Pelicula pelicula);
	Pelicula buscar(Integer idPelicula);
	List<Pelicula> listar();

}
