package promocion;

import java.util.List;

import atraccion.Atraccion;

public class PromocionAbsoluta implements Promocion {

	@Override
	public int calcularPromocion(List<Atraccion> atracciones, int descuento) {
		return sumarPreciosDeAtracciones(atracciones) - descuento;
	}

	private int sumarPreciosDeAtracciones(List<Atraccion> atracciones) {

		int precioAtracciones = 0;
		for (int i = 0; i < atracciones.size(); i++) {
			Atraccion objeto = atracciones.get(i);
			precioAtracciones += (int) objeto.obtenerCosto();
		}

		return precioAtracciones;
	}

}
