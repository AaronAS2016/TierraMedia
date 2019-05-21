package herramientas;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.JsonArray;

import atraccion.Atraccion;
import lector.Lector;

public class Calculadora {
	
	public static ArrayList<Atraccion> calcularAtracciones(JsonArray atracciones) {
		ArrayList<Atraccion> listaDeAtracciones = new ArrayList<Atraccion>();
		for(int i = 0; i < atracciones.size(); i++) {
			Atraccion atraccion = Lector.obtenerAtraccion(atracciones.get(i).getAsInt());
			listaDeAtracciones.add(atraccion);
		}
		return listaDeAtracciones;
	}
	
	public static double calcularTiempo(ArrayList<Atraccion> atracciones) {
		
		double tiempo = 0;
		Iterator<Atraccion> iteradorAtracciones = atracciones.iterator();
		while(iteradorAtracciones.hasNext()) {
			Atraccion atraccion = iteradorAtracciones.next();
			tiempo += atraccion.obtenerDuracion();
		}
		
		return tiempo;
	}
}
