package lector;

import java.io.FileReader;
import java.util.ArrayList;

import atraccion.Atraccion;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import turista.Turista;

public class Lector {

	
	
	
	public static ArrayList<Atraccion> obtenerAtracciones() {
		
		JsonArray listaDeAtracciones = leerItinerario("resources/itinerarios/atracciones.txt").getAsJsonArray();
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();

		for (int i = 0; i < listaDeAtracciones.size(); i++) {
			JsonObject dataAtraccion =  listaDeAtracciones.get(i).getAsJsonObject();
			int id = dataAtraccion.get("id").getAsInt();
			int costo = dataAtraccion.get("costo").getAsInt();
			double tiempo = dataAtraccion.get("tiempo").getAsDouble();
			int cupo = dataAtraccion.get("cupo").getAsInt();
			String nombre = dataAtraccion.get("nombre").getAsString();
			
			Atraccion atraccion = new Atraccion(id,costo,tiempo,cupo,nombre);
			atracciones.add(atraccion);
		}
		
		return atracciones;
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
