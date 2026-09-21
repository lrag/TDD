
Feature: Cesta de la compra 2

  Como comprador de la tienda online
  Quiero añadir varios productos distintos a la cesta en una sola operación
  Para agilizar una compra con muchos artículos sin repetir pasos uno a uno

  Scenario: El comprador añade varios productos de golpe a su cesta
    Given un comprador que selecciona varios productos distintos a la vez
    When confirma que quiere añadirlos todos a su cesta
    Then el sistema añade cada producto a la cesta y actualiza el importe total
