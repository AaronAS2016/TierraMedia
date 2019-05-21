package promocion;

import java.util.ArrayList;

import atraccion.Atraccion;

public class PromocionFija implements Promocion {

	@Override
	public int calcularPromocion(ArrayList<Atraccion> atracciones, int descuento) {
		return (sumarPrecios(atracciones) * descuento)/100;
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
