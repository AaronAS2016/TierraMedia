package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Iterator;
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
	public void cobrar2ProductosIguales() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		List<Producto> productos = recomendador.recomendar();
		Iterator<Producto> iterador  = productos.iterator();
		Producto productoAComprar = iterador.next();
		recomendador.aceptarProducto(productoAComprar);
		recomendador.aceptarProducto(productoAComprar);
		recomendador.generarFactura();
		assertTrue(new File("resources/itinerarios/factura.txt").exists());
	}

}
