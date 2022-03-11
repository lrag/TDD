package com.curso.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.curso.modelo.entidad.Pelicula;

/////////////////////////////////////////
//Ejemplo de como no implementar un DAO//
/////////////////////////////////////////
public class PeliculaDaoJDBCImplementation implements PeliculaDao {

	public void insertar(Pelicula pelicula) {
		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement("insert into pelicula (titulo, director, genero, year) values (?,?,?, ?)");
			pst.setString(1, pelicula.getTitulo());
			pst.setString(2, pelicula.getDirector());
			pst.setString(3, pelicula.getGenero());
			pst.setInt(4, pelicula.getYear());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void modificar(Pelicula pelicula) {
		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement("update pelicula set titulo=?, director=?, genero=?, year=? where id=?");
			pst.setString(1, pelicula.getTitulo());
			pst.setString(2, pelicula.getDirector());
			pst.setString(3, pelicula.getGenero());
			pst.setInt(4, pelicula.getYear());
			pst.setInt(5, pelicula.getId());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void borrar(Pelicula pelicula) {
		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement("delete from pelicula where id=?");
			pst.setInt(1, pelicula.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Pelicula> listar() {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();

		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement("select * from pelicula");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Pelicula pAux = new Pelicula(rs.getInt("ID"), 
											 rs.getString("TITULO"), 
											 rs.getString("DIRECTOR"),
											 rs.getString("GENERO"), 
											 rs.getInt("YEAR"));
				peliculas.add(pAux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return peliculas;
	}

	public Pelicula buscar(Integer idPelicula) {
		Pelicula p = null;

		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement("select * from pelicula where id=?");
			pst.setInt(1, idPelicula);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p = new Pelicula(rs.getInt("ID"), 
							     rs.getString("TITULO"), 
							     rs.getString("DIRECTOR"),
							     rs.getString("GENERO"), 
							     rs.getInt("YEAR"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return p;
	}

}
