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
	private ArrayList<Producto> productosRecomendados = null;

	public RecomendadorDeAtracciones(int idTurista) {
		if (idTurista < 0) {
			throw new Error("El ID debe ser un valor positivo");
		}
		turista = Lector.buscarTurista(idTurista);
		atracciones = Lector.obtenerAtracciones();
		paquetesPromocionales = Lector.obtenerPaquetes();
		productosAceptados = new ArrayList<Producto>();
	}
	
	
	
	public ArrayList<Producto> recomendar(){
		if(productosRecomendados == null || productosRecomendados.size() == 0){
			productosRecomendados = new ArrayList<Producto>();
			ArrayList<PaquetePromocional> paquetes = obtenerPaquetesPromocionalesRecomendadas();
			ArrayList<Atraccion> atracciones = obtenerAtraccionesRecomendadas();
			agregarPaquetes(paquetes);
			agregarAtracciones(atracciones);
		}
		if(productosRecomendados.size() == 0){
			throw new Error("Lo lamentamos, ninguna atraccion en base a tus preferencias");
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
		removerProductoDeSugerencias(productoAceptado.obtenerId());
	}
	
	public void generarFactura() {
		Escritor escritor = new Escritor(turista, productosAceptados);
		try {
			escritor.escribirFactura();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	
	private ArrayList<PaquetePromocional> obtenerPaquetesPromocionalesRecomendadas(){
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
			
			if (	   paquete.obtenerCosto() <= turista.obtenerPresupuesto()
					&& paquete.obtenerDuracion() < turista.obtenerTiempoDisponible()
					&& paquete.obtenerTipo().equals(turista.obtenerPreferencia())) {
				paquetesSugeridos.add(paquete);
			}
		}
		return paquetesSugeridos;
	}

	private ArrayList<Atraccion> obtenerAtraccionesRecomendadas() {
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
			
			if (	   atraccion.obtenerCosto() <= turista.obtenerPresupuesto()
					&& atraccion.obtenerCuposDisponbiles() > 0
					&& atraccion.obtenerDuracion() < turista.obtenerTiempoDisponible()
					&& atraccion.obtenerTipo().equals(turista.obtenerPreferencia())) {
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


}
