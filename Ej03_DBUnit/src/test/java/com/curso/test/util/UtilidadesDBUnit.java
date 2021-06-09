
package com.curso.test.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;

public class UtilidadesDBUnit {

	public static void main(String[] args) {
		
		crearEsquema();
		crearDatos();		
		
		try {
			generateXML("org.h2.Driver", "jdbc:h2:file:c:/h2/tdd", "sa", "", "tdd", "input");
			//generatePartialXML("org.h2.Driver", "jdbc:h2:file:c:/h2/tdd", "sa", "", "tdd", "input");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearEsquema() {		
		System.out.println("=============================================");
		System.out.println("CREANDO EL ESQUEMA");
		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement("DROP TABLE IF EXISTS PELICULA; CREATE TABLE IF NOT EXISTS PELICULA(ID INT AUTO_INCREMENT PRIMARY KEY, TITULO VARCHAR(255), DIRECTOR VARCHAR(255), GENERO VARCHAR(255), YEAR INT)");
			int rs = pst.executeUpdate();
			System.out.println("Resultado:"+rs);
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
	
	public static void crearDatos() {		
		System.out.println("=============================================");
		System.out.println("INSERTANDO LOS DATOS");

		String consultas = 
			"delete from pelicula;"+
			"insert into pelicula (titulo, director, genero, year) values ('T1','D1','G1',1);"+
			"insert into pelicula (titulo, director, genero, year) values ('T2','D2','G2',2);"+
			"insert into pelicula (titulo, director, genero, year) values ('T3','D3','G3',3);"+
			"insert into pelicula (titulo, director, genero, year) values ('T4','D4','G4',4);"+
			"insert into pelicula (titulo, director, genero, year) values ('T5','D5','G5',5);";
		
		Connection cx = null;
		try {
			cx = DriverManager.getConnection("jdbc:h2:file:c:/h2/tdd", "sa", "");
			PreparedStatement pst = cx.prepareStatement(consultas);
			int rs = pst.executeUpdate();
			System.out.println("Resultado:"+rs);
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
		
	public static void generateXML(String driverName, 
			                       String urlDB,
			                       String userDB, 
			                       String passwordDB, 
			                       String schemaBD, 
			                       String nameXML) throws SQLException {

		Connection conn = null;

		try {
			// Connect to the database
			Class.forName(driverName);
			conn = DriverManager.getConnection(urlDB, userDB, passwordDB);

			IDatabaseConnection connection = new DatabaseConnection(conn);

			DatabaseSequenceFilter filter = new DatabaseSequenceFilter(connection);
			IDataSet datasetAll = new FilteredDataSet(filter, connection.createDataSet());
			QueryDataSet dataSet = new QueryDataSet(connection);

			String[] listTableNames = filter.getTableNames(datasetAll);
			for (int i = 0; i < listTableNames.length; i++) {
				final String tableName = listTableNames[i];
				// Añadir las tablas al DataSet, en este caso se añaden todas
				dataSet.addTable(tableName);
			}

			// Especificar la ubicación del fichero a generar
			FlatXmlWriter datasetWriter = new FlatXmlWriter(
					new FileOutputStream("src/test/resources/db/" + nameXML + ".xml"));

			// Generar el fichero
			datasetWriter.write(dataSet);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public static void generatePartialXML(String driverName, String urlDB,
			String userDB, String passwordDB, String schemaBD, String nameXML)
			throws SQLException {
		Connection conn = null;

		try {
			// Connectar con la BBDD
			Class.forName(driverName);
			conn = DriverManager.getConnection(urlDB, userDB, passwordDB);
			IDatabaseConnection connection = new DatabaseConnection(conn); // , schemaBD);

			QueryDataSet partialDataSet = new QueryDataSet(connection);
			// Especificar que tablas formaran parte del Dataset
			partialDataSet.addTable("pelicula");

			// Especificar la ubicación del fichero a generar
			FlatXmlWriter datasetWriter = new FlatXmlWriter(
					new FileOutputStream("src/test/resources/db/" + nameXML + ".xml"));

			// Generar el fichero
			datasetWriter.write(partialDataSet);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}

	/*public static void createData(String driverName, String urlDB,
			String userDB, String passworDB, String nameXML)
			throws SQLException {

		Connection conn = null;
		try {
			// Connect to the database
			DriverManager.registerDriver((Driver) Class.forName(driverName)
					.newInstance());
			conn = DriverManager.getConnection(urlDB, userDB, passworDB);
			IDatabaseConnection connection = new DatabaseConnection(conn);

			DatabaseOperation.INSERT.execute(connection, new FlatXmlDataSet(
					new FileInputStream("db/" + nameXML + ".xml")));

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public static void deleteData(String driverName, String urlDB,
			String userDB, String passworDB, String nameXML)
			throws SQLException {
		Connection conn = null;
		try {
			// Connect to the database
			DriverManager.registerDriver((Driver) Class.forName(driverName)
					.newInstance());
			conn = DriverManager.getConnection(urlDB, userDB, passworDB);
			IDatabaseConnection connection = new DatabaseConnection(conn);
			DatabaseOperation.DELETE.execute(connection, new FlatXmlDataSet(
					new FileInputStream("db/" + nameXML + ".xml")));

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}*/

}