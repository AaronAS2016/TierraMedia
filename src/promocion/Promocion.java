package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public interface Promocion {
	public int calcularPromocion(ArrayList<Atraccion> atracciones, int descuento);
}
