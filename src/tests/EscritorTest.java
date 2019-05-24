package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
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
	
	@Test ( expected = Error.class )
	public void usuarioConId3CompraAtraccionYAlQuedarseSinMonedasIntentaComprarOtra() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		List<Producto> productos = recomendador.recomendar();
		recomendador.aceptarProducto(productos.get(0));
		recomendador.aceptarProducto(productos.get(1));
	}
	
	@Test 
	public void recomendarAUsuarioConId3LuegoDeHaberCompradoUnaAtraccion() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		List<Producto> productos = recomendador.recomendar();
		Iterator<Producto> iterador  = productos.iterator();
		Producto productoAComprar = iterador.next();
		recomendador.aceptarProducto(productoAComprar);
		
		assertEquals( "Lothlorien", productoAComprar.obtenerNombre() );
	}
	
	
	@Test
	public void usuarioConId3CompraUnaAtraccionYVuelveAVerificarSusRecomendaciones() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		List<Producto> productos = recomendador.recomendar();
		recomendador.aceptarProducto(productos.get(0));
		List<Producto> segundaRecomendacion = recomendador.recomendar();
		assertEquals( "Lothlorien", segundaRecomendacion.get(0).obtenerNombre() );
	}

	@Test
	public void usuarioConId3CompraUnaAtraccionYVuelveAVerificarSusRecomendaciones2() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		List<Producto> productos = recomendador.recomendar();
		//ACA EL PRODUCTO 2 ES LA COMARCA
		recomendador.aceptarProducto(productos.get(2));
		List<Producto> segundaRecomendacion = recomendador.recomendar();
		assertEquals( "Pack Aventura", segundaRecomendacion.get(2).obtenerNombre() );
	}
}
