<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>Listado de clientes</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
</head>

<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<body>

    <div class="page-header text-center" style="border-bottom: none;">
        <h1 class="titulo">
        	Inicio
        </h1>
    </div>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="inicio.jsp">Inicio</a></li>
                <li><a href="SVClientes">Clientes</a></li>
                <li><a href="#">Productos</a></li>
                <li><a href="#">Empleados</a></li>
                <li><a href="#" id="linkSalir">Salir</a></li>
            </ul>
            <p id="nombreUsuario" class="navbar-text navbar-right">Usuario: ${sessionScope.usuario.nombre}&nbsp;&nbsp;&nbsp;</p>
        </div>
    </nav>

    <form id="formSalir" action="../SVLogin" method="post" style="display: none;">
        <input type="hidden" name="accion" value="logout"/>
    </form>

    <script type="text/javascript">
        document.getElementById('linkSalir').addEventListener('click', function(event) {
            event.preventDefault();
            document.getElementById('formSalir').submit();
        });
    </script>

</body>
</html>





