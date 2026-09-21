
Feature: Mal uso de la cesta de la compra

  Como comprador legítimo pero descuidado
  Quiero introducir a toda prisa la cantidad de un producto
  Para completar mi compra rápido, sin fijarme si me he equivocado de cifra

  Scenario: El comprador introduce una cantidad desproporcionada por error
    Given un comprador que introduce la cantidad de un producto a toda prisa
    When se equivoca y pide una cantidad claramente desproporcionada
    Then el sistema rechaza la operación en vez de aceptar un pedido absurdo
