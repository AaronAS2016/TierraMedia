package tierramedia;

import java.util.ArrayList;

import atraccion.Atraccion;
import lector.Lector;
import turista.Turista;

public class RecomendadorDeAtracciones {

	
	private Turista turista;
	

	public RecomendadorDeAtracciones(int idTurista){
		if(idTurista < 0){
			throw new Error("El ID debe ser un valor positivo");
		}
		turista = Lector.buscarTurista(idTurista);
	}
	
	
	
	

	public ArrayList<Atraccion> ordenarAtracciones(ArrayList<Atraccion> atraccionesAOrdenar) {

		ArrayList<Atraccion> atraccionesOrdenadas;
		
		int largo = atraccionesAOrdenar.size();

		if (largo <= 1) {
			atraccionesOrdenadas = atraccionesAOrdenar;
		} else {

			ArrayList<Atraccion> primerMitad = new ArrayList<Atraccion>();
			for (int i = 0; i < (largo / 2 ); i++) {
				primerMitad.add(atraccionesAOrdenar.get(i));
			}

			ArrayList<Atraccion> segundaMitad = new ArrayList<Atraccion>();
			for (int i = 0; i < largo - (largo / 2); i++) {
				segundaMitad.add(atraccionesAOrdenar.get(i + largo / 2));
			}

			atraccionesOrdenadas = mezclarAtracciones(ordenarAtracciones(primerMitad), ordenarAtracciones(segundaMitad));
		}

		return atraccionesOrdenadas;
	}
	
	private ArrayList<Atraccion> mezclarAtracciones(ArrayList<Atraccion>primerMitad, ArrayList<Atraccion> segundaMitad) {

		ArrayList<Atraccion> mezcla = new ArrayList<Atraccion>(); 
		
		int iPrimerMitad = 0;
		int iSegundaMitad = 0;

		for (int i = 0; i < primerMitad.size() + segundaMitad.size(); i++) {

			if (iPrimerMitad >= primerMitad.size()) {
				mezcla.add(segundaMitad.get(iSegundaMitad));
				iSegundaMitad++;
			} else if (iSegundaMitad >= segundaMitad.size()) {
				mezcla.add(primerMitad.get(iPrimerMitad));
				iPrimerMitad++;
			} else if (primerMitad.get(iPrimerMitad).compareTo(segundaMitad.get(iSegundaMitad)) < 0) {
				mezcla.add(primerMitad.get(iPrimerMitad));
				iPrimerMitad++;

			} else {
				mezcla.add(segundaMitad.get(iSegundaMitad));
				iSegundaMitad++;
			}
		}

		return mezcla;
	}
	
	
	
}
