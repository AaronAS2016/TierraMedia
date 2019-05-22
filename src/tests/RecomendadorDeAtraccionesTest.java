package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import tierramedia.*;

import org.junit.Test;

public class RecomendadorDeAtraccionesTest {

	@Test
	public void obtenerPrimerProductoAlSugerirAUsuarioConId4() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(4);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Bosque Negro", productosRecomendados.get(0).obtenerNombre() );
	}
	
	
	
	/*Usuario con Id 5 ( Aragorn )*/
	
	@Test
	public void obtenerPrimerProductoAlSugerirAUsuarioConId5(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("La Comarca", productosRecomendados.get(0).obtenerNombre() );
	}
	
	@Test
	public void obtenerSegundoProductoAlSugerirAUsuarioConId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Mordor", productosRecomendados.get(1).obtenerNombre() );
	}
	
	
	@Test
	public void obtenerTercerProductoAlSugerirAUsuarioConId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Erebor", productosRecomendados.get(2).obtenerNombre() );
	}
	
	@Test
	public void obtenerCuartoProductoAlSugerirAUsuarioConId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Moria", productosRecomendados.get(3).obtenerNombre() );
	}
	
	@Test
	public void obtenerQuintoProductoAlSugerirAUsuarioConId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Minas Tirith", productosRecomendados.get(4).obtenerNombre() );
	}
	
	@Test
	public void obtenerSextoProductoAlSugerirAUsuarioConId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Abismo de Helm", productosRecomendados.get(5).obtenerNombre() );
	}
	
	@Test
	public void obtenerSeptimoProductoAlSugerirAUsuarioConId5() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Bosque Negro", productosRecomendados.get(6).obtenerNombre() );
	}
	
	
	
	
	
	
	
	/*Usuario con Id 3 ( Sam )*/
	
	@Test
	public void obtenerPrimerProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Pack Degustacion", productosRecomendados.get(0).obtenerNombre() );
	}
	
	@Test
	public void obtenerSegundoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Lothloriem", productosRecomendados.get(1).obtenerNombre() );
	}
	
	@Test
	public void obtenerTercerProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("La Comarca", productosRecomendados.get(2).obtenerNombre() );
	}
	
	@Test
	public void obtenerCuartoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Mordor", productosRecomendados.get(3).obtenerNombre() );
	}
	
	@Test
	public void obtenerQuintoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Erebor", productosRecomendados.get(4).obtenerNombre() );
	}
	
	@Test
	public void obtenerSextoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Moria", productosRecomendados.get(5).obtenerNombre() );
	}
	
	@Test
	public void obtenerSeptimoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Minas Tirith", productosRecomendados.get(6).obtenerNombre() );
	}
	
	@Test
	public void obtenerOctavoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Abismo de Helm", productosRecomendados.get(7).obtenerNombre() );
	}
	
	@Test
	public void obtenerNovenoProductoAlSugerirAUsuarioConId3(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(3);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Bosque Negro", productosRecomendados.get(8).obtenerNombre() );
	}
	
	
	
	
	
	
	
	
	
	/*Usuario con Id 1 ( Fromo )*/
	@Test
	public void obtenerPrimerProductoAlSugeriAUsuarioDeId1(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(1);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Moria", productosRecomendados.get(0).obtenerNombre() );
	}
	
	@Test
	public void obtenerSegundoProductoAlSugeriAUsuarioDeId1(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(1);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Bosque Negro", productosRecomendados.get(1).obtenerNombre() );
	}
	
	@Test
	public void obtenerTercerProductoAlSugeriAUsuarioDeId1(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(1);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Minas Tirith", productosRecomendados.get(2).obtenerNombre() );
	}
	
	@Test
	public void obtenerCuartoProductoAlSugeriAUsuarioDeId1(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(1);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Abismo de Helm", productosRecomendados.get(3).obtenerNombre() );
	}
	
	@Test
	public void obtenerQuintoProductoAlSugeriAUsuarioDeId1(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(1);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("La Comarca", productosRecomendados.get(4).obtenerNombre() );
	}
	
	
	
	
}
