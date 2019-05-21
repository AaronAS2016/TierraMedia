package herramientas;

import java.util.ArrayList;

import atraccion.Atraccion;

public class OrdenamientoDeAtracciones {
	
	public static ArrayList<Atraccion> ordenar(ArrayList<Atraccion> atraccionesAOrdenar) {

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

			atraccionesOrdenadas = mezclarAtracciones(ordenar(primerMitad), ordenar(segundaMitad));
		}

		return atraccionesOrdenadas;
	}
	
	public static ArrayList<Atraccion> mezclarAtracciones(ArrayList<Atraccion>primerMitad, ArrayList<Atraccion> segundaMitad) {

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
