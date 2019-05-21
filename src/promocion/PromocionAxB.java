package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public class PromocionAxB implements Promocion {

	@Override
	public int calcularPromocion(ArrayList<Atraccion> atracciones, int descuento) {
		return sumarPreciosDeAtracciones(atracciones) - buscarPrecioDeAtraccionGratis(atracciones, descuento);
	}

	private int sumarPreciosDeAtracciones(ArrayList<Atraccion> atracciones) {

		Atraccion atraccion;
		int precioAtracciones = 0;
		for (int i = 0; i < atracciones.size(); i++) {
			atraccion = atracciones.get(i);
			precioAtracciones += atraccion.obtenerCosto();
		}
		return precioAtracciones;
	}

	private int buscarPrecioDeAtraccionGratis(ArrayList<Atraccion> atracciones, int atraccionGratis) {

		Atraccion atraccion;
		int precioAtraccion = 0;
		boolean seEncontro = false;
		for (int i = 0; i < atracciones.size() && !seEncontro; i++) {
			atraccion = atracciones.get(i);
			seEncontro = (atraccion.obtenerId() == atraccionGratis);
			if (seEncontro) {
				precioAtraccion = atraccion.obtenerCosto();
			}
		}

		return precioAtraccion;
	}

}
