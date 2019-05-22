package lector;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import atraccion.Atraccion;
import herramientas.Calculadora;
import promocion.PaquetePromocional;
import promocion.Promocion;
import tierramedia.Producto;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import turista.Turista;

public class Lector {

	
	
	public static ArrayList<Producto> obtenerPaquetes(){
		
		JsonArray listaDePaquetesPromocionales = leerItinerario("resources/itinerarios/promociones.txt").getAsJsonArray();
		ArrayList<Producto> promociones = new ArrayList<Producto>();

		for (int i = 0; i < listaDePaquetesPromocionales.size(); i++) {
			JsonObject dataPromocion = listaDePaquetesPromocionales.get(i).getAsJsonObject();
			int id = dataPromocion.get("id").getAsInt();
			String nombre = dataPromocion.get("nombre").getAsString();
			int descuento = dataPromocion.get("descuento").getAsInt();
			ArrayList<Atraccion> atracciones = Calculadora.calcularAtracciones(dataPromocion.get("atracciones").getAsJsonArray());
			double tiempo = Calculadora.calcularTiempo(atracciones);
			String promocionTipo = dataPromocion.get("tipo_promocion").getAsString();
			String tipo = dataPromocion.get("tipo").getAsString();
			Promocion promocion = null;
			try {
				promocion = (Promocion) Class.forName("promocion."+promocionTipo).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			PaquetePromocional paquete = new PaquetePromocional(id, nombre, tiempo, descuento, atracciones, promocion, tipo);
			promociones.add(paquete);
		}
		return promociones;
	}
	
	



	public static ArrayList<Producto> obtenerAtracciones() {
		
		JsonArray listaDeAtracciones = leerItinerario("resources/itinerarios/atracciones.txt").getAsJsonArray();
		ArrayList<Producto> atracciones = new ArrayList<Producto>();

		for (int i = 0; i < listaDeAtracciones.size(); i++) {
			JsonObject dataAtraccion =  listaDeAtracciones.get(i).getAsJsonObject();
			int id = dataAtraccion.get("id").getAsInt();
			int costo = dataAtraccion.get("costo").getAsInt();
			double tiempo = dataAtraccion.get("tiempo").getAsDouble();
			int cupo = dataAtraccion.get("cupo").getAsInt();
			String nombre = dataAtraccion.get("nombre").getAsString();
			String tipo = dataAtraccion.get("tipo").getAsString();
			
			Atraccion atraccion = new Atraccion(id,costo,tiempo,cupo,nombre, tipo);
			atracciones.add(atraccion);
		}
		
		return atracciones;
	}
	
	public static Atraccion obtenerAtraccion(int idAtraccion) {
		
		Atraccion atraccionBuscada = null;
		
		ArrayList<Producto> listaDeAtracciones = obtenerAtracciones();
		Iterator<Producto> iteradorAtracciones = listaDeAtracciones.iterator();
		
		boolean seEncontro = false;
		
		while(iteradorAtracciones.hasNext() && !seEncontro){
			
			Producto atraccion = iteradorAtracciones.next();
			seEncontro = (idAtraccion == atraccion.obtenerId());
			
			if(seEncontro) {
				atraccionBuscada = (Atraccion) atraccion;
			}
		}
		
		if(!seEncontro){
			throw new Error("Atraccion no encontrada");
		}
		
		return atraccionBuscada;
	}
	
	
	
	public static Turista buscarTurista(int idTurista){
		
		JsonElement turistaBuscado = null;
		JsonArray turistas = leerItinerario("resources/itinerarios/turistas.txt").getAsJsonArray();
		for(int i = 0; i < turistas.size(); i++){
			JsonElement turista = turistas.get(i);
			
			int id = turista.getAsJsonObject().get("id").getAsInt();
			if (id == idTurista) {
				turistaBuscado = turista;
			}
		}
		
		if (turistaBuscado == null) {
			throw new Error("Usuario no encontrado");
		}
		
		JsonObject turista = turistaBuscado.getAsJsonObject();
		
		String nombre = turista.get("nombre").getAsString();
		double tiempo = turista.get("tiempo").getAsDouble();
		int presupuesto = turista.get("presupuesto").getAsInt();
		String preferencia = turista.get("preferencias").getAsString();
		return new Turista(idTurista, nombre, tiempo, presupuesto, preferencia);
	}
	
    public static JsonElement leerItinerario(String rutaDelArchivo) {
    	JsonElement archivoJson = null;
    	try{
    		archivoJson = new JsonParser().parse(new FileReader(rutaDelArchivo));
    	}catch(Exception e){
    		throw new Error("No se ha podido leer el archivo :" + e.getMessage());
    	}
    	return archivoJson;
    }
}
