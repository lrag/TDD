
Feature: Autenticación de usuarios

  Como usuario de la aplicación
  Quiero identificarme con mis credenciales
  Para poder acceder al área de gestión de clientes

  #Scenario: DEMASIADO ESPECÍFICO Login correcto
  #  Given estoy en la página de login
  #  When introduzco el login "aaa" y la contraseña "bbb"
  #  And pulso "Entrar"
  #  Then se crea mi sesión
  #  And veo la página de inicio
    
  Scenario: Login correcto
    #Por convenio decidimos que "entro en" es navegar directamente a esa página
    Given entro en la página de login
    When introduzco unas credenciales correctas
    And pulso el botón entrar 
    Then estoy autenticado
    And estoy en la página de inicio   
    
  Scenario: Login incorrecto
    Given entro en la página de login
    When introduzco unas credenciales incorrectas
    And pulso el botón entrar 
    Then estoy en la página de login
    And veo el mensaje "Credenciales incorrectas"    