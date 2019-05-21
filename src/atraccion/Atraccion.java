package atraccion;

public class Atraccion implements Comparable<Atraccion>{
	
	private int id;
	private int costo;
	private double tiempo;
	private int cupo;
	private String nombre;
		
	public Atraccion(int id,  int costo, double tiempo, int cupo, String nombre){
		this.id = id;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.nombre = nombre;
	}
	
	public String obtenerNombre(){
		return this.nombre;
	}
	
    public int obtenerId() {
		return id;
	}
    
    public int obtenerCosto() {
        return costo;
    }

    public double obtenerDuracion() {
        return tiempo;
    }
    public int obtenerCuposDisponbiles() {
        return cupo;
    }

	@Override
	public int compareTo(Atraccion atraccionAComparar) {
		int orden = -1;
		boolean esMayor = (this.obtenerCosto() > atraccionAComparar.obtenerCosto());
		if(esMayor){
			orden = 1;
		}else if(this.obtenerCosto() == atraccionAComparar.obtenerCosto()) {
			orden = 0;
		}
		return orden;
	}
		
}