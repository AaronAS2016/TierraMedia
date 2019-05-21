package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import lector.Lector;
import promocion.PaquetePromocional;

import org.junit.Test;

import atraccion.Atraccion;
import turista.Turista;

public class LectorTest {

	@Test
	public void leerNombreConTuristaDeId1() {
		Turista turista = Lector.buscarTurista(1);
		assertEquals("Fromo", turista.obtenerNombre());
	}
	
	@Test
	public void leerPresupuestoConTuristaDeId2() {
		Turista turista = Lector.buscarTurista(2);
		assertEquals(100, turista.obtenerPresupuesto());
	}
	
	@Test
	public void leerTiempoConTuristaDeId3() {
		Turista turista = Lector.buscarTurista(3);
		assertEquals(8, turista.obtenerTiempoDisponible(), 0.1);
	}

	@Test
	public void leerPreferenciaConTuristaDeId2() {
		Turista turista = Lector.buscarTurista(2);
		assertEquals("Paisajes", turista.obtenerPreferencia());
	}
	
	@Test(expected = Error.class)
	public void leerTuristaConId0() {
		Lector.buscarTurista(0);
	}
	
	@Test(expected = Error.class)
	public void leerTuristaConId19() {
		Lector.buscarTurista(19);
	}

	
	@Test
	public void leerIdConTuristaDeId1() {
		Turista turista = Lector.buscarTurista(1);
		assertEquals(1, turista.obtenerId());
	}

	
	@Test
	public void leerNombreConTuristaDeId3() {
		Turista turista = Lector.buscarTurista(3);
		assertEquals("Sam." , turista.obtenerNombre());
	}

	
	@Test
	public void leerPreferenciaConTuristaDeId3() {
		Turista turista = Lector.buscarTurista(3);
		assertEquals("Degustacion", turista.obtenerPreferencia());
	}
	
	@Test
	public void leerAtraccionConId1() {
		ArrayList<Atraccion> atracciones = Lector.obtenerAtracciones();
		Atraccion atraccion = atracciones.get(0);
		assertEquals("Moria", atraccion.obtenerNombre());
	}
	
	
	@Test
	public void leerAtraccionConId2Costo() {
		ArrayList<Atraccion> atracciones = Lector.obtenerAtracciones();
		Atraccion atraccion = atracciones.get(1);
		assertEquals(5, atraccion.obtenerCosto(), 0.1);
	}

	
	@Test
	public void leerAtraccionConId5TIempoDisponible() {
		ArrayList<Atraccion> atracciones = Lector.obtenerAtracciones();
		Atraccion atraccion = atracciones.get(4);
		assertEquals("Abismo de Helm", atraccion.obtenerNombre());
	}
	
	
	@Test
	public void obtenerNombreDeAtraccionConId1() {
		Atraccion atraccion = Lector.obtenerAtraccion(1);
		assertEquals("Moria", atraccion.obtenerNombre());
	}
	
	@Test
	public void obtenerDuracionDeAtraccionConId2() {
		Atraccion atraccion = Lector.obtenerAtraccion(2);
		assertEquals(2.5, atraccion.obtenerDuracion(), 0.1);
	}
	
	@Test
	public void obtenerCuposDeAtraccionConId2() {
		Atraccion atraccion = Lector.obtenerAtraccion(3);
		assertEquals(150, atraccion.obtenerCuposDisponbiles());
	}
	
	@Test(expected = Error.class)
	public void obtenerAtraccionConId0() {
		Lector.obtenerAtraccion(0);
	}
	
	@Test
	public void obtenerNombrePaquetePromocionalConId1() {
		ArrayList<PaquetePromocional> paquetes = Lector.obtenerPaquetes();
		assertEquals("Pack Aventura", paquetes.get(0).obtenerNombre());
	}
	
	@Test
	public void obtenerNombrePaquetePromocionalConId2() {
		ArrayList<PaquetePromocional> paquetes = Lector.obtenerPaquetes();
		assertEquals("Pack Degustacion", paquetes.get(1).obtenerNombre());
	}


}
