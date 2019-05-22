package herramientas;

public class Validador {

	
	public static String primeraLetraMayuscula(String str) {
		  if (str == null || str.isEmpty()) {
		    return str;            
		  } else {
		    return str.substring(0, 1).toUpperCase() + str.substring(1); 
		  }
	}
}
