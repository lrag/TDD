package com.proyecto;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;

import com.proyecto.modelo.entidad.Cesta;
import com.proyecto.modelo.entidad.GestorPedidos;
import com.proyecto.modelo.entidad.Pedido;
import com.proyecto.modelo.entidad.Producto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MalUsoPedido {

	private Cesta cesta;
	private Pedido pedido;
	private final GestorPedidos gestorPedidos = new GestorPedidos();

	@Given("una cesta con un teclado de precio {double}")
	public void una_cesta_con_un_teclado_de_precio(Double precio) {
		cesta = new Cesta();
		cesta.addProducto(new Producto(1, "Teclado", precio), 1);
	}

	@Given("un pedido con identificador de envío {string}")
	public void un_pedido_con_identificador_de_envio(String identificadorEnvio) {
		pedido = new Pedido(identificadorEnvio, LocalDate.now(), "Calle Falsa 123", cesta);
	}

	@When("confirmo el pedido dos veces seguidas")
	public void confirmo_el_pedido_dos_veces_seguidas() {
		gestorPedidos.confirmarPedido(pedido);
		gestorPedidos.confirmarPedido(pedido);
	}

	@Then("solo se debe registrar un pedido")
	public void solo_se_debe_registrar_un_pedido() {
		Assertions.assertEquals(1, gestorPedidos.getPedidos().size());
	}

}
