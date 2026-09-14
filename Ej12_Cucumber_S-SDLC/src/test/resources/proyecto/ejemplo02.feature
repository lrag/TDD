
Feature: Cesta de la compra 2
  Scenario: Comprar un teclado
    Given los siguientes productos
      | nombre producto | precio | cantidad |
      | Chintáfono      | 20     | 1        |
      | Fleje           | 30     | 2        |
      | Studebaker      | 40     | 3        |
    
    When Compro la cesta
    Then el total es 200.0