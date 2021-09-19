package actividad5;
import java.io.Serializable;

public class ObtieneFichero implements Serializable {

	// Declaración atributo con el contenido en bytes del fichero a descargar
	byte[] contenidoFichero ;
	
	// Constructor
	public ObtieneFichero(byte[] contenidoFichero) {
		
		this.contenidoFichero = contenidoFichero;
	}
	
	// Método para obtener el fichero a descargar
	public byte[] getContenidoFichero() {return contenidoFichero;}
	
} 