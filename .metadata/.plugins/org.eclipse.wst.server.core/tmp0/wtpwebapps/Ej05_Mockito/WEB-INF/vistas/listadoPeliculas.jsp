<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PINÍCULAS</title>
</head>
<body>

	<h1 align="center">
		<font color="lightGreen">
			Listado de películas
		</font>
	</h1>

	<p align="center">
		<a href="verFormulario">Nuevo</a>
	</p>

	<table align="center" border="1">
		<tr>
			<th>Título</th>
			<th>Director</th>
			<th>Género</th>
			<th>Fecha Estreno</th>
		</tr>		
		
		<c:forEach var="p" items="${listaPeliculas}">
			<tr>
				<td>
					<c:url var="url" value="seleccionar">
						<c:param name="id" value="${p.id}"/>
					</c:url>					
					<a href="${url}">${p.titulo}</a>
				</td>			
				<td>${p.director}</td>			
				<td>${p.genero}</td>			
				<td>${p.year}</td>			
			</tr>
		</c:forEach>
	</table>

</body>
</html>






