package tierramedia;

import herramientas.OrdenamientoDeAtracciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import escritor.Escritor;
import atraccion.Atraccion;
import lector.Lector;
import turista.Turista;

public class RecomendadorDeAtracciones {

	private Turista turista;
	private ArrayList<Producto> productosAceptados;
	private ArrayList<Producto> atracciones;
	private ArrayList<Producto> paquetesPromocionales;
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
	
	
	
	/** 
	 * 
	 * <p>post: Devuelve la información de los productos sugeridos para el usuario</p>
	 * 
	 * @return ArrayList<Producto> lista con los productos recomendados
	 * 
	 * */
	
	
	public ArrayList<Producto> recomendar(){
		
		
		boolean verificarTIpo = false;
		if(productosRecomendados.size() == 0){
			 
			calcularAtraccionesRecomendadas(verificarTIpo);
			verificarTIpo = (productosRecomendados.size() >= 0);
		}
		
		if(verificarTIpo){
			calcularAtraccionesRecomendadas(verificarTIpo);
		}
		
		if(productosRecomendados.size() == 0){
			throw new Error("No se encontro ninguna atraccion de su tipo");
		}
		
		return this.productosRecomendados;
	}
	
	
	
	/**
	 * 
	 * 
	 * <p> post: Se acepta el producto y se agrega a la cuenta, eliminando el producto aceptado de las posibles sugerencias </p>
	 * @param productoAceptado Producto que desea aceptar el usuario
	 * 
	 * */
	
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
	
	/**
	 * <p>post: Genera la factura con todos los productos aceptados del usuario </p>
	 * */
	
	public void generarFactura() {
		Escritor escritor = new Escritor(turista, productosAceptados);
		try {
			escritor.escribirFactura();
		} catch (IOException e) {
			e.printStackTrace();
		}
		actualizarItinerarios();
	}
	
	
	
	/**
	 * 
	 * <p>post: Calcula las atracciones sugeridads para el usuario</p>
	 * 
	 * @param verificarTIpo Si es true, se ignorarar la verificacion de tipo a la hora de ver si un producto es recomendado para el turista
	 * */
	private void calcularAtraccionesRecomendadas(boolean verificarTIpo) {
		ArrayList<Producto> paquetesRecomendados = obtenerProductosRecomendadas(paquetesPromocionales,verificarTIpo);
		ArrayList<Producto> atraccionesRecomendadas = obtenerProductosRecomendadas(atracciones,verificarTIpo);
		agregarProductos(OrdenamientoDeAtracciones.ordenar(paquetesRecomendados));
		agregarProductos(OrdenamientoDeAtracciones.ordenar(atraccionesRecomendadas));
	}

	
	
	private void agregarProductos(ArrayList<Producto> paquetes) {
		Iterator<Producto> iteradorProducto = paquetes.iterator();
		while(iteradorProducto.hasNext()){
			Producto producto = iteradorProducto.next();
			if(!verificarSugerencia(producto.id)){				
				this.productosRecomendados.add(producto);
			}
		}
	}
	


	
	
	private ArrayList<Producto> obtenerProductosRecomendadas(ArrayList<Producto> listaDeProductos, boolean verificarTIpo){
		ArrayList<Producto> paquetesSugeridos = new ArrayList<Producto>();
		Iterator<Producto> iteradorPaquetes = listaDeProductos.iterator();
		while (iteradorPaquetes.hasNext()) {
			Producto paquete = iteradorPaquetes.next();
			boolean leAlcanza = (paquete.obtenerCosto() <= turista.obtenerPresupuesto());
			boolean tieneTiempo = (paquete.obtenerDuracion() < turista.obtenerTiempoDisponible());
			boolean esDeSuTipo = paquete.obtenerTipo().equals(turista.obtenerPreferencia());
			System.out.println("--------------------------");
			System.out.println("Paquete:" + paquete.obtenerNombre());
			System.out.println("leAlcanza:" + leAlcanza + " / Dinero atraccion = " + paquete.obtenerCosto() + " - Dinero turista:" + turista.obtenerPresupuesto());
			System.out.println("tieneTiempo:" + tieneTiempo + " / Tiempo atraccion = " + paquete.obtenerDuracion() + " - Tiempo turista:" + turista.obtenerTiempoDisponible());
			System.out.println("esDeSuTipo:" + esDeSuTipo + " / Tipo atraccion = " + paquete.obtenerTipo() + " - Tipo turista:" + turista.obtenerPreferencia() );
			System.out.println("verificarTipo:" + verificarTIpo);
			System.out.println("--------------------------");
			
			if (leAlcanza && tieneTiempo && (esDeSuTipo || verificarTIpo)) { 
				paquetesSugeridos.add(paquete);
			}
		}
		return paquetesSugeridos;
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
		Iterator<Producto> iteradorAtracciones = atracciones.iterator();
		boolean seEncontro = false;
		while(iteradorAtracciones.hasNext()){
			seEncontro = (iteradorAtracciones.next().obtenerId() == obtenerId);
		}
		return seEncontro;
	}
	
	private boolean verificarSugerencia(int obtenerId) {
		Iterator<Producto> iteradorAtracciones = productosRecomendados.iterator();
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
