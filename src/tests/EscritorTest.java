package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import tierramedia.*;

import org.junit.After;
import org.junit.Test;

public class EscritorTest {
	
	List<Producto> productosRecomendados;
	Iterator<Producto> iterador;

	@Test
	public void escribirFacturaAceptandoUnaCompraConElTuristaDeId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		productosRecomendados = recomendador.recomendar();
		recomendador.aceptarProducto(productosRecomendados.get(0));
		recomendador.generarFactura();
		assertTrue(new File("resources/itinerarios/factura.txt").exists());
	}
	
	
	@Test(expected = Error.class)
	public void cobrar2ProductosIguales() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		productosRecomendados = recomendador.recomendar();
		iterador  = productosRecomendados.iterator();
		Producto productoAComprar = iterador.next();
		recomendador.aceptarProducto(productoAComprar);
		recomendador.aceptarProducto(productoAComprar);
		recomendador.generarFactura();
		assertTrue(new File("resources/itinerarios/factura.txt").exists());
	}
	
	@Test ( expected = Error.class )
	public void usuarioConId3CompraAtraccionYAlQuedarseSinMonedasIntentaComprarOtra() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		productosRecomendados = recomendador.recomendar();
		recomendador.aceptarProducto(productosRecomendados.get(0));
		recomendador.aceptarProducto(productosRecomendados.get(1));
	}
	
	
	@Test(expected = Error.class)
	public void usuarioConId3CompraUnaAtraccionYVuelveAVerificarSusRecomendaciones() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		productosRecomendados = recomendador.recomendar();
		Producto producto = productosRecomendados.get(0);
		recomendador.aceptarProducto(producto);
		recomendador.recomendar();
	}

	@Test(expected = Error.class)
	public void usuarioConId3CompraUnaAtraccionYVuelveAVerificarSusRecomendaciones2() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		productosRecomendados = recomendador.recomendar();
		//ACA EL PRODUCTO 2 ES LA COMARCA
		Producto producto = productosRecomendados.get(2);
		recomendador.aceptarProducto(producto);
		productosRecomendados = recomendador.recomendar();
	}
	
	@After public void afterAll() {
		iterador = this.productosRecomendados.iterator();
		System.out.println("--------LISTA DE PRODUCTOS-----------");
		while(iterador.hasNext()) {
			Producto producto = iterador.next();
			System.out.println("Nombre: " + producto.obtenerNombre());
		}
		System.out.println("-------------------");
	}
}
