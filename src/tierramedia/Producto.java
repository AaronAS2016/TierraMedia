package tierramedia;

import java.util.ArrayList;

import atraccion.Atraccion;

public abstract class Producto {
	
	protected int id;
	protected int costo;
	protected double duracion;
	protected String nombre;
	protected String tipo;
	protected ArrayList<Atraccion> atracciones;
	protected String tipoProducto;
	
	public int obtenerId() {
		return this.id;
	}

	public String obtenerNombre (){
		return nombre;
	}
	public int obtenerPrecio (){
		return this.costo;
	}
	
	public double obtenerDuracion (){
		return this.duracion;
	}
	
	public int obtenerCosto(){
		return this.costo;
	}
	
	public String obtenerTipo(){
		return this.tipo;
	}
	
	public ArrayList<Atraccion> obtenerAtracciones(){
		return this.atracciones;
	}
	
	public String obtenerTipoDeProducto() {
		return this.tipoProducto;
	}
}
