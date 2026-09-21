
Feature: Cesta de la compra

  Como comprador de la tienda online
  Quiero añadir productos a mi cesta y conocer el importe a pagar
  Para decidir si confirmo la compra antes de pagar

  Scenario: El comprador conoce el importe de su cesta antes de pagar
    Given un comprador con productos añadidos a su cesta
    When consulta el importe total de su cesta
    Then el sistema le muestra cuánto va a pagar en total

  Scenario: El comprador añade el mismo producto más de una vez
    Given un comprador que ya tiene un producto en su cesta
    When vuelve a añadir el mismo producto
    Then su cesta refleja la cantidad acumulada, no una línea repetida
