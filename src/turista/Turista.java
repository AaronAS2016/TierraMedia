package turista;

public class Turista {

	private int id;
	private String nombre;
    private int presupuesto;
    private double tiempoDisponible;
    private String preferencia;
    
    public Turista(int id, String nombre, double tiempoDisponible, int presupuesto, String preferencia){

    	this.id = id;
    	this.nombre = nombre;
    	this.tiempoDisponible = tiempoDisponible;
    	this.preferencia = preferencia;
    	this.presupuesto = presupuesto;
    }

    public int obtenerPresupuesto() {
        return this.presupuesto;
    }

    public void cobrarAtraccion(int costo, double tiempo) {
    	if(costo > presupuesto){
    		throw new Error("Presupuesto no suficiente para cobrar esta atracción");
    	}
    	if(tiempo > tiempoDisponible) {
    		throw new Error("No tiene tiempo suficiente para comprar esta atracción");
    	}
        this.presupuesto -= costo;
        this.tiempoDisponible -= tiempo;
    }

    public void descontarTiempo(double tiempoDeAtraccion) {
        this.tiempoDisponible -= tiempoDeAtraccion;
    }

    public String obtenerPreferencia() {
        return this.preferencia;
    }
    
    public int obtenerId(){
    	return this.id;
    }
    
    public String obtenerNombre(){
    	return this.nombre;
    }
    
    public double obtenerTiempoDisponible(){
    	return this.tiempoDisponible;
    }
    

}