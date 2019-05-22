package atraccion;

import tierramedia.Producto;

public class Atraccion extends Producto implements Comparable<Atraccion> {

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

	@Override
	public int compareTo(Atraccion atraccionAComparar) {
		int orden = -1;
		boolean esMayor = (this.obtenerCosto() > atraccionAComparar
				.obtenerCosto());
		if (esMayor) {
			orden = 1;
		} else if (this.obtenerCosto() == atraccionAComparar.obtenerCosto()) {
			orden = 0;
		}
		return orden;
	}

}