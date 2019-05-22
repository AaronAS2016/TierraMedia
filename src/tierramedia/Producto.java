package tierramedia;

public abstract class Producto {
	
	protected int id;
	protected int costo;
	protected double duracion;
	protected String nombre;
	protected String tipo;
	
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
}
