package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import tierramedia.*;

import org.junit.Test;

public class EscritorTest {

	@Test
	public void escribirFacturaAceptandoUnaCompraConElTuristaDeId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		List<Producto> productos = recomendador.recomendar();
		recomendador.aceptarProducto(productos.get(0));
		recomendador.generarFactura();
		assertTrue(new File("resources/itinerarios/factura.txt").exists());
	}
	
	
	@Test(expected = Error.class)
	public void escribirCasoDeError() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		List<Producto> productos = recomendador.recomendar();
		recomendador.aceptarProducto(productos.get(0));
		recomendador.aceptarProducto(productos.get(0));
		recomendador.generarFactura();
		assertTrue(new File("resources/itinerarios/factura.txt").exists());
	}

}
