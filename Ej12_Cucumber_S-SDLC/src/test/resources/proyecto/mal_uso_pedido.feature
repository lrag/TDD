
Feature: Mal uso del pedido de compra

  Como comprador legítimo
  Quiero confirmar mi pedido
  Para recibir mis productos, aunque a veces pulse "comprar" dos veces por impaciencia

  Scenario: Doble envío del mismo pedido por doble clic
    Given una cesta con un teclado de precio 100.0
    And un pedido con identificador de envío "ENVIO-123"
    When confirmo el pedido dos veces seguidas
    Then solo se debe registrar un pedido
