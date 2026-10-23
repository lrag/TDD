Feature: Cesta de la compra 2

  Scenario: Comprar varias cosas
    Given los siguientes productos
      | nombre producto | precio | cantidad |
      | Chintáfono      | 20     | 1        |
      | Fleje           | 30     | 2        |
      | Studebaker      | 40     | 3        |
    When Compro la cesta
    Then el total de la cesta es 200.0     
    And hay 3 productos en la cesta   
    
  Scenario Outline: Ejecución de un escenario varias veces con distintos datos
    When Quiero imprimir por consola "<numero>" y "<mensaje>"
    Then Todo fue estupendamente con el texto "<resultado>"

    Examples:
      | numero | mensaje | resultado  |
      | 100    | HOLA    | Estupendo! |
      | 200    | QUE     | Chanchi!   |
      | 300    | TAL     | Fetén!     |
      
  Scenario Outline: Comprar un producto
    Given el precio de "<producto>" es <precio>
    When Compro <unidades> producto
    Then el total de la cesta es <total>.     
    
    Examples:
      | producto           | precio | unidades | total |
      | Chintáfono         | 10     | 1        | 10    |
      | Fleje              | 20     | 2        | 40    |
      | Elemento disruptor | 300    | 3        | 900   |    
    
    
    
     