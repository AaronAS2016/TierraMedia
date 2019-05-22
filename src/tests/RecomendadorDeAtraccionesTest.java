package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import tierramedia.*;

import org.junit.Test;

public class RecomendadorDeAtraccionesTest {

	@Test
	public void sugerirAUsuarioConId4() {
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(1);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("Bosque Negro", productosRecomendados.get(0).obtenerNombre() );
	}
	
	@Test
	public void sugerirUsuarioConId5(){
		RecomendadorDeAtracciones recomendador = new RecomendadorDeAtracciones(5);
		ArrayList<Producto> productosRecomendados = recomendador.recomendar();
		assertEquals("La Comarca", productosRecomendados.get(0).obtenerNombre() );
	}
	

}
