Feature: Autenticación de usuarios

  Como usuario de la aplicación
  Quiero identificarme con mis credenciales
  Para poder acceder al área de gestión de clientes

  #Scenario: DEMASIADO ESPECÍFICO Login correcto
  #  Given estoy en la página de login
  #  When introduzco el login "aaa" y la contraseña "bbb"
  # And pulso "Entrar"
  #  Then se crea mi sesión
  # And veo el listado de clientes
    
  Scenario: Login correcto
    Given estoy en la página de login
    When introduzco mis credenciales
    And pulso el botón entrar 
    Then se crea mi sesión
    And veo la página de inicio
    
  Scenario: Login incorrecto
    Given estoy en la página de login
    When introduzco unas credenciales incorrectas
    And pulso el botón entrar 
    And estoy en la página de login
    #And veo el mensaje "Credenciales incorrectas"    
    
    
    
    
    