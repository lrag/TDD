
Feature: Listado de clientes

  Como usuario autenticado
  Quiero ver el listado de clientes
  Para consultar quién está dado de alta

  Scenario: Ver el listado con clientes existentes
    #Given hay clientes dados de alta
    When accedo al listado de clientes
    Then veo una fila por cada cliente con su nombre, dirección y teléfono

