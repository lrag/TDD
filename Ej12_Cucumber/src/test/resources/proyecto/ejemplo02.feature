Feature: Cesta de la compra 2
  Scenario: Comprar varias cosas
    Given los siguientes productos
      | nombre producto | precio | cantidad |
      | Chintáfono      | 20     | 1        |
      | Fleje           | 30     | 2        |
      | Studebaker      | 40     | 3        |
    
    When Compro la cesta
    Then el total es 200.0 y el numero de detalles es 3
    
Scenario Outline: Ejecución de un escenario varias veces con distintos datos
    When Quiero imprimir por consola "<numero>" y "<mensaje>"
    Then Todo fue estupendamente

    Examples:
      | numero | mensaje |
      | 100    | HOLA    |
      | 200    | QUE     |
      | 300    | TAL     |