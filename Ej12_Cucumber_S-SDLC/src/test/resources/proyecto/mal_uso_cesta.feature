
Feature: Mal uso de la cesta de la compra

  Como comprador legítimo pero descuidado
  Quiero introducir a toda prisa la cantidad de un producto
  Para completar mi compra rápido, sin fijarme si me he equivocado de cifra

  Scenario: Cantidad desproporcionada por error de tecleo
    Given el precio de un teclado es 100.0
    When Compro 10000 teclados
    Then la operación es rechazada
