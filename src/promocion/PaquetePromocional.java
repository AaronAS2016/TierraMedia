package promocion;

import java.util.ArrayList;

import tierramedia.Producto;
import atraccion.Atraccion;

public class PaquetePromocional extends Producto {

	private int descuento;
	private Promocion promocion;

	public PaquetePromocional(int id, String nombre, double duracion, int descuento,
			ArrayList<Atraccion> atracciones, Promocion promocion, String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.descuento = descuento;
		this.promocion = promocion;
		this.atracciones = atracciones;
		this.tipo = tipo;
		this.costo = calcularPromocion();
		this.tipoProducto = "Paquete";
	}

	public int calcularPromocion() {
		return promocion.calcularPromocion(atracciones, descuento);
	}
	

}
