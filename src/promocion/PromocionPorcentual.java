package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public class PromocionPorcentual implements Promocion {

	@Override
	public int calcularPromocion(ArrayList<Atraccion> atracciones, int descuento) {
		int precioTotal = sumarPrecios(atracciones);
		int valorDelDescuento = (precioTotal * descuento) / 100;
		return precioTotal - valorDelDescuento;
	}
	
	private int sumarPrecios(ArrayList<Atraccion> atracciones){
		
		Atraccion atraccion;
		int precioAtracciones = 0;
		for (int i = 0; i < atracciones.size() ; i++){
			atraccion = atracciones.get(i);
			precioAtracciones += atraccion.obtenerCosto();
		}
		
		return precioAtracciones;
	}

}
