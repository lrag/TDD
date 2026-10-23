Feature: Autenticación de usuarios

  Como propietario de la aplicación
  Quiero que solo usuarios autenticados entren en /seguro/*
  Para proteger la gestión de clientes de accesos no autorizados
    
  #Scenario: Acceso a una URL segura sin sesión
	#"No tener sesión" no implica la ejecución de código alguno
	#Este escenario no necesita "Given"
    #Given no tengo una sesión iniciada
    #When intento acceder directamente a página de inicio
    #Then estoy en la página de login   
    
  #
  #Este escenario se ejecuta una vez por cada recurso a comprobar  
  #  
  Scenario Outline: Acceso a una URL segura sin sesión
    When intento acceder directamente a "<recurso>"
    Then estoy en la página de login

    Examples:
      | recurso                        |
      | /seguro/inicio.jsp             |
      | /seguro/SVClientes             |
      | /seguro/formularioClientes.jsp |    
      | /seguro/listadoClientes.jsp    |    
    
  #
  #Este escenario se ejecuta una única vez con la lista de recursos del tirón
  #    
  Scenario: Acceso a una URL segura sin sesión
    Given las siguientes páginas
      | recurso                        |
      | /seguro/inicio.jsp             |
      | /seguro/SVClientes             |
      | /seguro/formularioClientes.jsp |
      | /seguro/listadoClientes.jsp    |    
    When intento acceder directamente a cada una de ellas
    Then estoy en la página de login
        
    
    
    