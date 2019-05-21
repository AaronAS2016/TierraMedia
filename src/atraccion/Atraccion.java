package atraccion;

public class Atraccion implements Comparable<Atraccion>{
	
	private int id;
	private Integer costo;
	private Double tiempo;
	private int cupo;
	private String descripcion;
		
	public Atraccion(int id,  int costo, Double tiempo, int cupo, String descripcion){
		this.id = id;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.descripcion = descripcion;
	}
	
	public String obtenerDescripcion(){
		return this.descripcion;
	}
	
    public int obtenerId() {
		return id;
	}
    
    public Integer obtenerCosto() {
        return costo;
    }

    public Double obtenerDuracion() {
        return tiempo;
    }
    public int obtenerCuposDisponbiles() {
        return cupo;
    }

	@Override
	public int compareTo(Atraccion atraccionAComparar) {
		int orden;
		orden = obtenerCosto().compareTo(atraccionAComparar.obtenerCosto());
		if(orden == 0){
			orden = obtenerDuracion().compareTo(atraccionAComparar.obtenerDuracion());
		}
		return orden;
	}
		
}