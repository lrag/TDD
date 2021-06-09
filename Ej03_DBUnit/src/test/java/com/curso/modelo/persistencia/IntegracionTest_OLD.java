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
import org.junit.jupiter.api.extension.ExtendWith;
import org.xml.sax.InputSource;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;

public class IntegracionTest_OLD extends DatabaseTestCase {

	//Este es el objeto que queremos probar
	private PeliculaDao peliculaDao = new PeliculaDaoJDBCImplementation();

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
		System.out.println("Cargando el data set...");
		return new FlatXmlDataSet(new FlatXmlProducer(new InputSource("src/test/resources/db/input.xml")));
	}

	// Operación a realizar antes de empezar el test
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
	
		return DatabaseOperation.CLEAN_INSERT; //  .CLEAN_INSERT;
	}

	// Operacion a realizar despues del test
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE;
	}

	//@Test
	public void testCompareQuery() throws Exception {
		System.out.println("Test compare query");
		QueryDataSet queryDataSet = new QueryDataSet(getConnection());
		queryDataSet.addTable("pelicula", "SELECT * FROM pelicula");
		System.out.println(getDataSet());
		Assertion.assertEquals(getDataSet(), queryDataSet);
	}
	
	//@Test
	public void integracionPeliculaDao() throws Exception
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
				
		QueryDataSet queryDataSet = new QueryDataSet(getConnection());
		queryDataSet.addTable("pelicula", "SELECT TITULO, DIRECTOR, GENERO, YEAR FROM pelicula");		
		Assertion.assertEquals(expectedDataSet, queryDataSet);

	}
	
	@Test
	public void integeracionGestorPeliculas() throws Exception
	{
		//Aqui vamos a probar GestorPeliculas
		//Como se van a usar un gestorPelículas, un PeliculaDao de verdad y una base de datos esto no es un test
		//unitario si no de integración
		
		GestorPeliculas gp = new GestorPeliculas();
		
		IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("src/test/resources/db/expected.xml")));
		
		//Realizar las modificaciones que lleven al real al estado esperado
		Pelicula p1 = new Pelicula();
		p1.setId(5);
		gp.borrar(p1);
		
		Pelicula p2 = new Pelicula(null,"T6","D6","G6",6);
		gp.insertar(p2);
		
		Pelicula p3 = new Pelicula(1,"T111","D1","G1",1);
		gp.modificar(p3);
		Pelicula p4 = new Pelicula(2,"T2","D222","G2",2);
		gp.modificar(p4);
		Pelicula p5 = new Pelicula(3,"T3","D3","G333",3);
		gp.modificar(p5);
		Pelicula p6 = new Pelicula(4,"T4","D4","G4",444);
		gp.modificar(p6);				
		
		QueryDataSet queryDataSet = new QueryDataSet(getConnection());
		queryDataSet.addTable("pelicula", "SELECT TITULO, DIRECTOR, GENERO, YEAR FROM pelicula");		
		Assertion.assertEquals(expectedDataSet, queryDataSet);

	}	
	
	

}




