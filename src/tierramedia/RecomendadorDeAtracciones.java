package tierramedia;

import herramientas.OrdenamientoDeAtracciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import escritor.Escritor;
import atraccion.Atraccion;
import lector.Lector;
import turista.Turista;

public class RecomendadorDeAtracciones {

	private Turista turista;
	private List<Producto> productosAceptados;
	private List<Producto> atracciones;
	private List<Producto> paquetesPromocionales;
	private List<Producto> productosRecomendados;

	
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
		productosAceptados = new LinkedList<Producto>();
		productosRecomendados = new ArrayList<Producto>();
	}
	
	
	
	/** 
	 * 
	 * <p>post: Devuelve la información de los productos sugeridos para el usuario</p>
	 * 
	 * @return ArrayList<Producto> lista con los productos recomendados
	 * 
	 * */
	
	
	public List<Producto> recomendar(){
		System.out.println("---------- RECOMENDADOR DE ATRACCIONES ----------------");
		
		boolean verificarTIpo = false;

		calcularAtraccionesRecomendadas(verificarTIpo);
		verificarTIpo = (productosRecomendados.size() >= 0);
		
		if(verificarTIpo){
			calcularAtraccionesRecomendadas(verificarTIpo);
		}
		
		System.out.println("EL TAMAÑO DEL ARRAY  DE LAS RECOMENDACIONES: " + productosRecomendados.size());
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
		
		boolean comproProducto = verificarCompra(productosAceptados, productoAceptado.obtenerId());
		
		if(comproProducto){
			throw new Error("No puede comprar un producto que ya compro");
		}
		
		productosAceptados.add(productoAceptado);
		
		this.turista.cobrarAtraccion(productoAceptado.obtenerCosto(), productoAceptado.obtenerDuracion());
		
		if(productoAceptado.obtenerTipoDeProducto().equals("Paquete")){
			List<Atraccion> atraccionesDelPaquete = productoAceptado.obtenerAtracciones();
			Iterator<Atraccion> itAtracciones = atraccionesDelPaquete.iterator();
			while(itAtracciones.hasNext()) {
				Producto atraccion = itAtracciones.next();
				removerProductoDeSugerencias(atraccion.obtenerId());
			}
		}
		removerProductoDeSugerencias(productoAceptado.obtenerId());
		
		
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
	}
	
	
	
	/**
	 * 
	 * <p>post: Calcula las atracciones sugeridads para el usuario</p>
	 * 
	 * @param verificarTIpo Si es true, se ignorarar la verificacion de tipo a la hora de ver si un producto es recomendado para el turista
	 * */
	private void calcularAtraccionesRecomendadas(boolean verificarTIpo) {
		List<Producto> paquetesRecomendados = obtenerProductosRecomendadas(paquetesPromocionales,verificarTIpo);
		List<Producto> atraccionesRecomendadas = obtenerProductosRecomendadas(atracciones,verificarTIpo);
		agregarProductos(OrdenamientoDeAtracciones.ordenar(paquetesRecomendados));
		agregarProductos(OrdenamientoDeAtracciones.ordenar(atraccionesRecomendadas));
	}

	
	
	private void agregarProductos(List<Producto> paquetes) {
		Iterator<Producto> iteradorProducto = paquetes.iterator();
		while(iteradorProducto.hasNext()){
			Producto producto = iteradorProducto.next();
			if(!verificarCompra(productosRecomendados, producto.obtenerId())){				
				this.productosRecomendados.add(producto);
			}
		}
	}
	
	private List<Producto> obtenerProductosRecomendadas(List<Producto> listaDeProductos, boolean verificarTIpo){
		List<Producto> paquetesSugeridos = new ArrayList<Producto>();
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
			}else {
				removerProductoDeSugerencias(paquete.obtenerId());
			}
		}
		return paquetesSugeridos;
	}
	
	private void removerProductoDeSugerencias(int id) {
		boolean seEncontro = false;
		Iterator<Producto> itProducto = this.productosRecomendados.iterator();
		while(itProducto.hasNext() && !seEncontro) {
			Producto producto = itProducto.next();
			seEncontro = (producto.obtenerId() == id);
			if(seEncontro){
				productosRecomendados.remove(producto);
			}
		}
	}
	

	private boolean verificarCompra(List<Producto> producto, int idBuscado) {
		Iterator<Producto> iteradorProducto = producto.iterator();
		boolean seEncontro = false;
		while(iteradorProducto.hasNext() && !seEncontro){
			seEncontro = (iteradorProducto.next().obtenerId() == idBuscado);
		}
		return seEncontro;
	}
	

}
