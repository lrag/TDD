
Feature: Cesta de la compra
  Scenario: Comprar un teclado
    Given el precio de un teclado es 100.0
    When Compro 1 teclados
    Then el total es 100.0
    
  Scenario: Comprar dos teclados
    Given el precio de un teclado es 150.0
    When Compro 2 teclados
    Then el total es 300.0    
        
  Scenario: Comprar dos productos diferentes
    Given el precio de un teclado es 20.0
    And el precio de un raton es 15.0
    When Compro 1 teclados
    And Compro 1 raton
    Then el total es 35.0      
    
  Scenario: Comprar varias veces el mismo producto agrupa los detalles en la cesta
    Given el precio de un teclado es 20.0
    When Compro 1 teclados
    And Compro 1 teclados
    Then el numero de detalles de la cesta es 1      
    
    