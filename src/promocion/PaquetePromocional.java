package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public class PaquetePromocional {

	private int id;
	private String nombreDelPaquete;
	private double duracion;
	private int descuento;
	private ArrayList<Atraccion> atracciones;
	private Promocion promocion;

	public PaquetePromocional(int id, String nombreDelPaquete, double duracion, int descuento,
			ArrayList<Atraccion> atracciones, Promocion promocion) {
		this.id = id;
		this.nombreDelPaquete = nombreDelPaquete;
		this.duracion = duracion;
		this.descuento = descuento;
		this.atracciones = atracciones;
	}

	public int calcularPromocion() {
		return promocion.calcularPromocion(atracciones, descuento);
	}

	public int obtenerId() {
		return this.id;
	}

	public double obtenerDuracion() {
		return this.duracion;
	}

	public String obtenerNombre() {
		return this.nombreDelPaquete;
	}

}
