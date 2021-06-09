package com.curso.modelo.negocio;

import java.util.List;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.persistencia.PeliculaDao;
import com.curso.modelo.persistencia.PeliculaDaoJDBCImplementation;

public class GestorPeliculas {

	//Creamos aqui el dao por simplificar
	private PeliculaDao peliculaDao = new PeliculaDaoJDBCImplementation();
	
	
	public void insertar(Pelicula pelicula) {
		//LN
		//...
		peliculaDao.insertar(pelicula);		
	}

	public void modificar(Pelicula pelicula) {
		//LN
		//...
		peliculaDao.modificar(pelicula);
	}

	public void borrar(Pelicula pelicula) {
		//LN
		//...
		peliculaDao.borrar(pelicula);
	}

	public List<Pelicula> listar() {
		return peliculaDao.listar();
	}

	public Pelicula buscar(Integer id) {
		return peliculaDao.buscar(id);
	}

}
