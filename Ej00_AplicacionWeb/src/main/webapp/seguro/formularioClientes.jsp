<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>Listado de clientes</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
</head>

<body>

    <div class="page-header text-center">
        <h1 class="titulo">Formulario de clientes</h1> 
    </div>
    
	<form name="formulario" action="SVClientes" method="post">

		<input type="hidden" name="accion"/>

	    <div class="row">
	        <div class="col-xs-2"></div>  
	        <div class="text-center col-xs-8">  
				<input type="submit" id="btnInsertar"  value="Insertar"  class="btn btn-primary" onclick="document.formulario.accion.value='insertar'"/>
				<input type="submit" id="btnModificar" value="Modificar" class="btn btn-primary" onclick="document.formulario.accion.value='modificar'"/>
				<c:if test="${usuario.roles=='ADMIN'}">
					<input type="submit" id="btnBorrar"    value="Borrar"    class="btn btn-primary" onclick="document.formulario.accion.value='borrar'"/>
				</c:if>
				<input type="submit" value="Cancelar"  class="btn btn-primary"/>
	        </div>
	        <div class="col-xs-2"></div>  
	    </div>

		</br>
	
		<input type="hidden" name="idCliente" value="${clienteSel.id}"/>

	    <div class="row">
	        <div class="col-xs-2"></div>
	        <div class="col-xs-8 form-horizontal">
	            <div class="form-group">
	                <label class="control-label col-xs-2" for="nombre">Nombre</label>
	                <div class="col-xs-8">
	                    <input type="text" id="nombre" name="nombre" class="form-control" value="${clienteSel.nombre}"/>
	                </div>
	            </div>  
	            <div class="form-group">
	                <label class="control-label col-xs-2" for="director">Direcci�n</label>
	                <div class="col-xs-8">
	                    <input type="text" id="direccion" name="direccion" class="form-control" value="${clienteSel.direccion}"/>
	                </div>
	            </div>  
	            <div class="form-group">
	                <label class="control-label col-xs-2" for="telefono">Tel�fono</label>
	                <div class="col-xs-8">
	                    <input type="text" id="telefono" name="telefono" class="form-control" value="${clienteSel.telefono}"/>
	                </div>
	            </div>  
	        <div class="col-xs-2"></div>        
	        </div>
	    </div>

	</form>

</body>
</html>