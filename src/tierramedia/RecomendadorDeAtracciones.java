package tierramedia;

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
	
	
	
}
