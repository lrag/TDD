@Autenticado @Tocoto
Feature: Listado de clientes

  Como usuario autenticado
  Quiero ver el listado de clientes
  Para consultar quién está dado de alta

  #@Autenticado
  Scenario: Ver el listado con clientes existentes
  	#Esto está perfectamente bien pero lo vamos a hacer con decoradores
  	Given un usuario autenticado
    Given hay clientes dados de alta
    When accedo al listado de clientes
    Then veo una fila por cada cliente con su nombre, dirección y teléfono



