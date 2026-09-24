Feature: Autenticación de usuarios

  Como propietario de la aplicación
  Quiero que solo usuarios autenticados entren en /seguro/*
  Para proteger la gestión de clientes de accesos no autorizados
    
  Scenario: Acceso a una URL segura sin sesión
	#"No tener sesión" no implica la ejecución de código alguno
	#Este escenario no necesita "Given"
    #Given no tengo una sesión iniciada
    When intento acceder directamente a página de inicio
    Then estoy en la página de login   
    
    
    
    
    
    