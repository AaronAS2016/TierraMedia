package atraccion;

import tierramedia.Producto;

public class Atraccion extends Producto {

	private int cupo;

	public Atraccion(int id, int costo, double tiempo, int cupo, String nombre, String tipo) {
		this.id = id;
		this.costo = costo;
		this.duracion = tiempo;
		this.cupo = cupo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tipoProducto = "Atraccion";
	}

	public int obtenerCuposDisponbiles() {
		return cupo;
	}

}