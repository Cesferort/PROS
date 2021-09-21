package actividad5;
import java.io.Serializable;

public class PideFichero implements Serializable {
	
	// Declaración atributo con el nombre del fichero a descargar del servidor
	String nombreFichero;
	
	// Constructor
	public PideFichero (String nombreFichero) {
		this.nombreFichero=nombreFichero;
	}
	
	// Método para obtener el nombre del fichero a descargar 
	public String getNombreFichero() { return nombreFichero;}
}