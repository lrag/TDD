package com.curso.modelo.persistencia;

import java.util.List;

import com.curso.modelo.entidad.Cliente;

public class ClienteDaoImpl implements ClienteDao {

	@Override
	public Cliente insertar(Cliente cliente) {		
		System.out.println("insert into clientes...");
		cliente.setId(1);
		return cliente;
	}

	@Override
	public void modificar(Cliente obj) {
		//update...
		
	}

	@Override
	public void borrar(Cliente obj) {
		//detele...
		
	}

	@Override
	public Cliente buscar(Integer id) {
		//select...
		return null;
	}

	@Override
	public List<Cliente> listar() {
		//select...
		return null;
	}

}

/*


public class ClienteDao_MOCK implements ClienteDao {



	public Cliente insertar(Cliente obj) {	
		obj.setId(1);
		return obj;
	}

	public void modificar(Cliente obj) {
	}

	public void borrar(Cliente obj) {
	}

	public Cliente buscar(Integer id) {
		null;
	}

	public List<Cliente> listar() {
		return null;
	}

}


*/











