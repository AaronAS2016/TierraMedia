package tierramedia;

import herramientas.OrdenamientoDeAtracciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import promocion.PaquetePromocional;
import escritor.Escritor;
import atraccion.Atraccion;
import lector.Lector;
import turista.Turista;

public class RecomendadorDeAtracciones {

	private Turista turista;
	private ArrayList<Producto> productosAceptados;
	private ArrayList<Atraccion> atracciones;
	private ArrayList<PaquetePromocional> paquetesPromocionales;
	private ArrayList<Producto> productosRecomendados;

	
	/** 
	 * <h1>Recomendador de Atracciones </h1>
	 * 
	 * Para un determinado usuario le permite la funcionalidad de recibir recomendaciones, aceptar y generar la factura con todos los productos que acepte.
	 * 
	 * <br><br> pre: Itinerarios creados en resources con la informacion de las preferencias de usuario, atracciones y paquetes promocionales
	 * 
	 * @param idTurista: Id del turista que se le quiera recomendar atracciones
	 * 
	 * 
	 * */
	public RecomendadorDeAtracciones(int idTurista) {
		if (idTurista < 0) {
			throw new Error("El ID debe ser un valor positivo");
		}
		turista = Lector.buscarTurista(idTurista);
		atracciones = Lector.obtenerAtracciones();
		paquetesPromocionales = Lector.obtenerPaquetes();
		productosAceptados = new ArrayList<Producto>();
		productosRecomendados = new ArrayList<Producto>();
	}
	
	
	
	public ArrayList<Producto> recomendar(){
		
		
		boolean verificarTIpo = false;
		if(productosRecomendados.size() == 0){
			calcularAtraccionesRecomendadas(verificarTIpo);
		}
		verificarTIpo = (productosRecomendados.size() == 0);
		
		if(verificarTIpo){
			calcularAtraccionesRecomendadas(verificarTIpo);
		}
		
		if(productosRecomendados.size() == 0){
			throw new Error("No se encontro ninguna atraccion de su tipo");
		}
		
		return this.productosRecomendados;
	}
	
	
	
	public void aceptarProducto(Producto productoAceptado){
		
		boolean comproProducto = verificarCompra(productoAceptado.obtenerId());
		
		if(comproProducto){
			throw new Error("No puede comprar un producto que ya compro");
		}
		productosAceptados.add(productoAceptado);
		turista.cobrarAtraccion(productoAceptado.obtenerCosto(), productoAceptado.obtenerDuracion());
		if(productoAceptado.obtenerTipoDeProducto().equals("Paquete")){
			ArrayList<Atraccion> atraccionesDelPaquete = productoAceptado.obtenerAtracciones();
			for(int i = 0; i < atraccionesDelPaquete.size(); i++){
				removerProductoDeSugerencias(atraccionesDelPaquete.get(i).obtenerId());
			}
		}else {
			removerProductoDeSugerencias(productoAceptado.obtenerId());
		}
		
	}
	
	public void generarFactura() {
		Escritor escritor = new Escritor(turista, productosAceptados);
		try {
			escritor.escribirFactura();
		} catch (IOException e) {
			e.printStackTrace();
		}
		actualizarItinerarios();
	}
	
	
	
	private void calcularAtraccionesRecomendadas(boolean verificarTIpo) {
		ArrayList<PaquetePromocional> paquetes = obtenerPaquetesPromocionalesRecomendadas(verificarTIpo);
		ArrayList<Atraccion> atracciones = obtenerAtraccionesRecomendadas(verificarTIpo);
		agregarPaquetes(paquetes);
		agregarAtracciones(atracciones);
	}

	
	private void agregarPaquetes(ArrayList<PaquetePromocional> paquetes) {
		Iterator<PaquetePromocional> iteradorProducto = paquetes.iterator();
		while(iteradorProducto.hasNext()){
			this.productosRecomendados.add(iteradorProducto.next());
		}
	}



	private void agregarAtracciones(ArrayList<Atraccion> producosParaAgregar){
		Iterator<Atraccion> iteradorProducto = producosParaAgregar.iterator();
		while(iteradorProducto.hasNext()){
			this.productosRecomendados.add(iteradorProducto.next());
		}
	}
	
	
	private ArrayList<PaquetePromocional> obtenerPaquetesPromocionalesRecomendadas(boolean verificarTIpo){
		ArrayList<PaquetePromocional> paquetesSugeridos = new ArrayList<PaquetePromocional>();
		Iterator<PaquetePromocional> iteradorPaquetes = paquetesPromocionales.iterator();
		while (iteradorPaquetes.hasNext()) {
			PaquetePromocional paquete = iteradorPaquetes.next();
			boolean leAlcanza = (paquete.obtenerCosto() <= turista.obtenerPresupuesto());
			boolean tieneTiempo = (paquete.obtenerDuracion() < turista.obtenerTiempoDisponible());
			boolean esDeSuTipo = paquete.obtenerTipo().equals(turista.obtenerPreferencia());
			System.out.println("--------------------------");
			System.out.println("Paquete:" + paquete.obtenerNombre());
			System.out.println("leAlcanza:" + leAlcanza + " / Dinero atraccion = " + paquete.obtenerCosto() + " - Dinero turista:" + turista.obtenerPresupuesto());
			System.out.println("tieneTiempo:" + tieneTiempo + " / Tiempo atraccion = " + paquete.obtenerDuracion() + " - Tiempo turista:" + turista.obtenerTiempoDisponible());
			System.out.println("esDeSuTipo:" + esDeSuTipo + " / Tipo atraccion = " + paquete.obtenerTipo() + " - Tipo turista:" + turista.obtenerPreferencia() );
			System.out.println("--------------------------");
			
			if (leAlcanza && tieneTiempo && (esDeSuTipo || verificarTIpo)) { 
				paquetesSugeridos.add(paquete);
			}
		}
		return paquetesSugeridos;
	}

	private ArrayList<Atraccion> obtenerAtraccionesRecomendadas(boolean verificarTIpo) {
		ArrayList<Atraccion> atraccionesSugeridas = new ArrayList<Atraccion>();
		Iterator<Atraccion> iteradorAtracciones = atracciones.iterator();
		while (iteradorAtracciones.hasNext()) {
			Atraccion atraccion = iteradorAtracciones.next();
			boolean leAlcanza = (atraccion.obtenerCosto() <= turista.obtenerPresupuesto());
			boolean hayCupo = (atraccion.obtenerCuposDisponbiles() > 0);
			boolean tieneTiempo = (atraccion.obtenerDuracion() < turista.obtenerTiempoDisponible());
			boolean esDeSuTipo = atraccion.obtenerTipo().equals(turista.obtenerPreferencia());
			
			System.out.println("--------------------------");
			System.out.println("Atraccion:"+ atraccion.obtenerNombre());
			System.out.println("leAlcanza:" + leAlcanza + " / Dinero atraccion = " + atraccion.obtenerCosto() + " - Dinero turista:" + turista.obtenerPresupuesto());
			System.out.println("hayCupo:" + hayCupo);
			System.out.println("tieneTiempo:" + tieneTiempo + " / Tiempo atraccion = " + atraccion.obtenerDuracion() + " - Tiempo turista:" + turista.obtenerTiempoDisponible());
			System.out.println("esDeSuTipo:" + esDeSuTipo + " / Tipo atraccion = " + atraccion.obtenerTipo() + " - Tipo turista:" + turista.obtenerPreferencia() );
			System.out.println("--------------------------" );
			
			if (leAlcanza && hayCupo && tieneTiempo && (esDeSuTipo || verificarTIpo)) {
					   atraccionesSugeridas.add(atraccion);
			}
		}
		return OrdenamientoDeAtracciones.ordenar(atraccionesSugeridas);
	}
	

	private void removerProductoDeSugerencias(int id) {
		boolean seEncontro = false;
		for(int i = 0; i < this.productosRecomendados.size() && !seEncontro; i++){
			seEncontro = (productosRecomendados.get(i).obtenerId() == id);
			if(seEncontro){
				productosRecomendados.remove(i);
			}
		}
	}
	

	private boolean verificarCompra(int obtenerId) {
		Iterator<Atraccion> iteradorAtracciones = atracciones.iterator();
		boolean seEncontro = false;
		while(iteradorAtracciones.hasNext()){
			seEncontro = (iteradorAtracciones.next().obtenerId() == obtenerId);
		}
		return seEncontro;
	}
	
	private void actualizarItinerarios() {
		//TODO: Completar metodo
	}


}
