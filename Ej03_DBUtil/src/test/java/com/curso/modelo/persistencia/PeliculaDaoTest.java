package com.curso.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.Assertion;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import com.curso.modelo.entidad.Pelicula;

public class PeliculaDaoTest extends DatabaseTestCase {

	private PeliculaDao peliculaDao = new PeliculaDaoJDBCImplementation();
	private IDataSet loadedDataSet;

	// Conexion a nuestra base de datos
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("org.h2.Driver");
		Connection jdbcConnection = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
		return new DatabaseConnection(jdbcConnection);
	}

	// El estado con el que queremos empezar las pruebas de nuestra base de datos
	@Override
	protected IDataSet getDataSet() throws Exception {
		loadedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("src/test/resources/db/input.xml")));
		return loadedDataSet;
	}

	// Operación a realizar antes de empezar el test
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	// Operacion a realizar despues del test
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	@Test
	public void testCompareQuery() throws Exception {
		QueryDataSet queryDataSet = new QueryDataSet(getConnection());
		queryDataSet.addTable("pelicula", "SELECT * FROM pelicula");
		Assertion.assertEquals(loadedDataSet, queryDataSet);
	}
	
	@Test
	public void testCompareDataSet() throws Exception
	{
		IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("src/test/resources/db/expected.xml")));
		
		//Realizar las modificaciones que lleven al real al estado esperado
		Pelicula p1 = new Pelicula();
		p1.setId(5);
		peliculaDao.borrar(p1);
		
		Pelicula p2 = new Pelicula(null,"T6","D6","G6",6);
		peliculaDao.insertar(p2);
		
		Pelicula p3 = new Pelicula(1,"T111","D1","G1",1);
		peliculaDao.modificar(p3);
		Pelicula p4 = new Pelicula(2,"T2","D222","G2",2);
		peliculaDao.modificar(p4);
		Pelicula p5 = new Pelicula(3,"T3","D3","G333",3);
		peliculaDao.modificar(p5);
		Pelicula p6 = new Pelicula(4,"T4","D4","G4",444);
		peliculaDao.modificar(p6);
				
		String[] tablas = {"pelicula"};
		
		IDataSet realDataSet = getConnection().createDataSet(tablas);
		
		Assertion.assertEquals(expectedDataSet, realDataSet);
	}
	

}




