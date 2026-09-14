
Feature: Abuso de la cesta de la compra

  Scenario: Un atacante intenta añadir un producto con precio negativo
    Given el precio de un teclado es -50.0
    When Compro 1 teclados
    Then la operación es rechazada

  Scenario: Un atacante intenta añadir una cantidad negativa para reducir el total
    Given el precio de un teclado es 100.0
    When Compro -3 teclados
    Then la operación es rechazada

  Scenario: Un atacante reutiliza el id de un producto para sustituir su precio
    Given el precio de un teclado es 100.0
    And Compro 1 teclados
    When intento añadir un producto con el mismo id y precio 1.0
    Then el total sigue siendo 100.0
