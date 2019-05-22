package tests;

import java.util.ArrayList;

import tierramedia.*;

import org.junit.Test;

public class EscritorTest {

	@Test
	public void escribirFacturaAceptandoUnaCompraConElTuristaDeId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productos = recomendador.recomendar();
		recomendador.aceptarProducto(productos.get(0));
		recomendador.generarFactura();
	}

}
