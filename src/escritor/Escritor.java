package escritor;

import herramientas.Validador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import tierramedia.Producto;
import turista.Turista;

public class Escritor {
	
	private List<Producto> paquetes;
	private Turista usuarioActual;
	private int precioTotal;
	private double tiempoTotal;
	private FileWriter factura;

	
	public Escritor(Turista usuarioActual, List<Producto> paquetes){
	
		this.paquetes = paquetes;
		this.usuarioActual = usuarioActual;
		this.precioTotal = 0;
		this.tiempoTotal = 0;
		
		try
        {
			this.factura = new FileWriter("resources/itinerarios/factura.txt");
			
        } catch (Exception escepcion){
			escepcion.printStackTrace();
        }
		
	}
	
	public void escribirFactura() throws IOException{
		factura.write("=================== FACTURA ======================\n");
		factura.write("Id de usuario:" + usuarioActual.obtenerId() + "\n");
		factura.write("Nombre:" + usuarioActual.obtenerNombre() + "\n");
		factura.write("Tiempo Disponible:" + usuarioActual.obtenerTiempoDisponible() + "hs." + "\n");
		factura.write("Tipo preferido:" + Validador.primeraLetraMayuscula(usuarioActual.obtenerPreferencia()) + "\n");
		factura.write("Presupuesto Disponible:" + usuarioActual.obtenerPresupuesto() + "$" +"\n");
		factura.write("\n");
		
		factura.write("=================== COMPRAS ======================\n");
		factura.write("\n");
		
		for (int i = 0; i < paquetes.size(); i++){
			
			Producto paquete = paquetes.get(i);
			factura.write("Paquete:" + paquete.obtenerNombre() + "\n");
			factura.write("Tiempo:" + paquete.obtenerDuracion()+ "hs." +"\n");
			factura.write("Precio:" + paquete.obtenerPrecio()+  "$" + "\n");
			factura.write("\n");
			precioTotal +=  paquete.obtenerPrecio();
			tiempoTotal += paquete.obtenerDuracion();
		}
		
		factura.write("=================== RESUMEN ======================\n");
		factura.write("Precio total:" + precioTotal + "$" + "\n");
		
		factura.write("Tiempo total:" + tiempoTotal + "hs." +"\n");
		factura.write("\n\nGracias por su compra, " + usuarioActual.obtenerNombre() + " :D \n");
		factura.close();
	}
	
}