<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script type="text/javascript">

function setActionFormulario(accion){
	document.getElementById("formulario").action = accion;
}

</script>

<body>

	<h1 align="center">
		<font color="orange">
			Formulario de películas
		</font>
	</h1>

	<form:form id="formulario" method="POST" modelAttribute="pelicula">
	
		<p align="center">
			<input type="submit" value="Insertar"  onclick="setActionFormulario('insertar')"/>
			<input type="submit" value="Modificar" onclick="setActionFormulario('modificar')"/>
			<input type="submit" value="Borrar"    onclick="setActionFormulario('borrar')"/>
			<a href="verListado">Cancelar</a>
		</p>
		
		<form:hidden path="id"/>
	
		<p align="center">
			<font color="red">${mensaje}</font>
		</p>
	
		<table align="center">
			<tr>
				<td>Título</td>
				<td>
					<form:input path="titulo"/>
				</td>
			</tr>
			<tr>
				<td>Director</td>
				<td>
					<form:input path="director"/>
				</td>
			</tr>
			<tr>
				<td>Género</td>
				<td>
					<form:input path="genero"/>
				</td>
			</tr>
			<tr>
				<td>Fecha de estreno</td>
				<td>
					<form:input path="year"/>
				</td>
			</tr>
		</table>
	
	</form:form>


</body>
</html>







































