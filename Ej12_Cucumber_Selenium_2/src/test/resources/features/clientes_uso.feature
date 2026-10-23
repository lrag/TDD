@Autenticado @Tocoto
Feature: Clientes

  #@Autenticado
  Scenario: Ver el listado con clientes existentes
	
	Como usuario autenticado
  	Quiero ver el listado de clientes
  	Para consultar quién está dado de alta
  	
  	#Esto está perfectamente bien pero lo vamos a hacer con decoradores
  	#Given un usuario autenticado
    Given hay clientes dados de alta
    #Por convenio decidimos que "navego a" es utilizando el menú
    When navego a la pagina del listado de clientes
    Then veo una fila por cada cliente con su nombre, dirección y teléfono

  #@Autenticado
  Scenario: Selección de un cliente
	
	Como usuario autenticado
  	Quiero seleccionar un cliente
  	
  	#No queremos repetir esto en todas partes
  	#Given un usuario autenticado
    When entro en la página de listado de clientes
    And selecciono un cliente de la tabla
    Then veo el formulario con el cliente seleccionado   
    
    
    
    
    