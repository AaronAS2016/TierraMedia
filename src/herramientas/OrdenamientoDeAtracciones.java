package herramientas;

import java.util.ArrayList;
import tierramedia.Producto;

public class OrdenamientoDeAtracciones {
	
	public static ArrayList<Producto> ordenar(ArrayList<Producto> atraccionesAOrdenar) {

		ArrayList<Producto> atraccionesOrdenadas;
		
		int largo = atraccionesAOrdenar.size();

		if (largo <= 1) {
			atraccionesOrdenadas = atraccionesAOrdenar;
		} else {

			ArrayList<Producto> primerMitad = new ArrayList<Producto>();
			for (int i = 0; i < (largo / 2 ); i++) {
				primerMitad.add(atraccionesAOrdenar.get(i));
			}

			ArrayList<Producto> segundaMitad = new ArrayList<Producto>();
			for (int i = 0; i < largo - (largo / 2); i++) {
				segundaMitad.add(atraccionesAOrdenar.get(i + largo / 2));
			}

			atraccionesOrdenadas = mezclarAtracciones(ordenar(primerMitad), ordenar(segundaMitad));
		}

		return atraccionesOrdenadas;
	}
	
	public static ArrayList<Producto> mezclarAtracciones(ArrayList<Producto>primerMitad, ArrayList<Producto> segundaMitad) {

		ArrayList<Producto> mezcla = new ArrayList<Producto>(); 
		
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
