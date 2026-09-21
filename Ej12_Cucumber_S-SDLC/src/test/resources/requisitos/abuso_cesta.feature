
Feature: Abuso de la cesta de la compra

  Como atacante externo
  Quiero manipular el precio, la cantidad o la identidad de un producto
  Para pagar menos de lo que corresponde por mi compra

  Scenario: Un atacante manipula el precio o la cantidad para reducir el importe a pagar
    Given un atacante que conoce el funcionamiento de la cesta de la compra
    When intenta añadir un producto con un precio o una cantidad inválidos
    Then el sistema rechaza la operación y no altera el importe a pagar

  Scenario: Un atacante suplanta un producto legítimo para alterar su precio
    Given un atacante que conoce el identificador de un producto ya añadido a la cesta
    When intenta añadir ese mismo producto con un precio distinto
    Then el sistema rechaza la operación y mantiene el importe original
