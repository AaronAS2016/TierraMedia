package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import tierramedia.*;

import org.junit.Test;

public class EscritorTest {

	@Test
	public void escribirFacturaAceptandoUnaCompraConElTuristaDeId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productos = recomendador.recomendar();
		recomendador.aceptarProducto(productos.get(0));
		recomendador.generarFactura();
		assertTrue(new File("resources/itinerarios/factura.txt").exists());
	}

}
