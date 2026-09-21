
Feature: Mal uso del pedido de compra

  Como comprador legítimo
  Quiero confirmar mi pedido
  Para recibir mis productos, aunque a veces pulse "comprar" dos veces por impaciencia

  Scenario: El comprador envía el mismo pedido más de una vez sin querer
    Given un comprador que confirma su pedido
    When repite el envío del mismo pedido por un doble clic o un reenvío
    Then el sistema registra un único pedido, no uno por cada intento
