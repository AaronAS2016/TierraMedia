package promocion;
import java.util.List;

import atraccion.Atraccion;

public interface Promocion {
	public int calcularPromocion(List<Atraccion> atracciones, int descuento);
}
