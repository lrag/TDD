package com.curso.modelo.persistencia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.curso.modelo.entidad.Pelicula;
import com.curso.modelo.negocio.GestorPeliculas;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.DBUnitExtension;

@ExtendWith(DBUnitExtension.class)
@DBUnit(driver = "org.h2.Driver", 
        url = "jdbc:h2:file:c:/h2/tdd", 
        user = "sa", 
        password = "")
public class IntegracionTest {
		
	/*
	Para una clase que no tiene @ExtendsWith: 
	 
	crear una instancia de IntegracionTest
	invocar el método que esté marcado con @BeforeAll
	incovar el método que esté marcado con @BeforeEach
	invocar el método marcado que @Test 
	incovar el método que esté marcado con @AfterEach
	invocar el método que esté marcado con @AfterAll
	
	Con el @ExtendsWith(DBUnitExtension.class):

	crear una instancia de IntegracionTest
	invocar el método que esté marcado con @BeforeAll
	incovar el método que esté marcado con @BeforeEach
	cargar en la base de datos el dataset indicado con @DataSet
	invocar el método marcado con @Test 
	comparar el estado de la base de datos con el dataset indicado en @ExpectedDataSet
	incovar el método que esté marcado con @AfterEach
	invocar el método que esté marcado con @AfterAll
	*/
	
	@Test
	@DataSet("db/input.xml")
	@ExpectedDataSet(value="db/expected.xml" /*,ignoreCols = "id"*/)
	public void integracionPeliculaDao() throws Exception
	{
		//Este es el objeto que queremos probar
		PeliculaDao peliculaDao = new PeliculaDaoJDBCImplementation();

		System.out.println("=====================================");
		peliculaDao.listar().forEach(p -> System.out.println(p));		
		
		//Realizar las modificaciones que lleven a la base de datos al estado esperado
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
		
		//No hacen falta asertos porque ya hemos puesto @ExpectedDataSet	
	}
		
	@Test
	@DataSet("db/input.xml")
	@ExpectedDataSet(value="db/expected.xml" /*,ignoreCols = "id"*/)	
	public void integeracionGestorPeliculas() throws Exception
	{
		//Aqui vamos a probar GestorPeliculas
		//Como se van a usar un gestorPelículas, un PeliculaDao de verdad y una base de datos esto no es un test
		//unitario si no de integración		
		//
		//Esta prueba tiene mucho más sentido que la anterior. No hace falta que probemos los DAOs/repositorios
		GestorPeliculas gp = new GestorPeliculas();
		
		System.out.println("=====================================");
		gp.listar().forEach(p -> System.out.println(p));
		
		//Podemos crear programáticamente los datasets:
		//IDataSet expectedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("src/test/resources/db/expected.xml")));
		
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
	}

}




