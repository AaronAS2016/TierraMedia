package tierramedia;
import java.util.List;

import atraccion.Atraccion;

public abstract class Producto implements Comparable<Producto>{
	
	protected int id;
	protected int costo;
	protected double duracion;
	protected String nombre;
	protected String tipo;
	protected List<Atraccion> atracciones;
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
	
	public List<Atraccion> obtenerAtracciones(){
		return this.atracciones;
	}
	
	public String obtenerTipoDeProducto() {
		return this.tipoProducto;
	}
	
	
	@Override
	public int compareTo(Producto productoAComparar) {
		int orden = -1;
		boolean esMayor = (this.obtenerCosto() > productoAComparar
				.obtenerCosto());
		if (esMayor) {
			orden = 1;
		} else if (this.obtenerCosto() == productoAComparar.obtenerCosto()) {
			
			if(this.obtenerDuracion() > productoAComparar.obtenerDuracion()) {
				orden = 1;
			}else if(this.obtenerDuracion() == productoAComparar.obtenerDuracion()) {
				orden = 0;
			}else {
				orden = -1;
			}
		}
		return orden;
	}
}
