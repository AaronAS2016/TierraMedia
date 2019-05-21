package tests;

import static org.junit.Assert.*;
import lector.Lector;

import org.junit.Test;

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


}
