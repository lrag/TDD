<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cesta</title>
</head>

<%
System.out.println(session.getId()+" (cesta.jsp)");
%>

<script type="text/javascript">

function mensaje(){
	console.log("Mensaje")
}

window.onload = mensaje

</script>

<body>

	<h2 align="center">
		<font color="lightGreen">
			Cesta de productos
		</font>
	</h2>
	
	<form name="formulario" action="SVCesta" method="post">
		<table align="center" border="0">
			<tr>
				<td>Producto</td>
				<td>
					<input type="text" name="producto"/>
				</td>
			</tr>
			<tr>
				<td>Cantidad</td>
				<td>
					<input type="text" name="cantidad"/>
				</td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					<input type="submit" id="btnEnviar" value="Añadir"/>
				</td>
			</tr>
		</table>
	</form>
	
	<p/>

	<table align="center" border="1" width="400px" id="tablaDetalles">
		<tr>
			<th>Producto</th>
			<th>Cantidad</th>
		</tr>
		<%System.out.println(session.getAttribute("cesta"));%> 
		<c:forEach var="detalle" items="${cesta}">
			<tr>
				<td>${detalle.producto}</td>			
				<td>${detalle.cantidad}</td>			
			</tr>		
		</c:forEach>
	</table>

</body>
</html>