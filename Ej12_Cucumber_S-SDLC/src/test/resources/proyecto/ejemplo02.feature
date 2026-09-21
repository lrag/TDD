
Feature: Cesta de la compra 2
  
  Como comprador de la tienda online
  Quiero añadir varios productos distintos a la cesta en una sola operación
  Para agilizar una compra con muchos artículos sin repetir pasos uno a uno
  
  Scenario: Comprar varios productos
    Given los siguientes productos
      | nombre producto | precio | cantidad |
      | Chintáfono      | 20     | 1        |
      | Fleje           | 30     | 2        |
      | Studebaker      | 40     | 3        |
    
    When Compro la cesta
    Then el total es 200.0
    