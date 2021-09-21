package ejemplo9;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente 
{

	public static void main (String[] args) throws IOException, ClassNotFoundException 
	{
		String Host="localhost";
		int Puerto = 6000;
		System.out.println("PROGRAMA CLIENTE INICIANDO");
		Socket cliente = new Socket(Host, Puerto);

		// Obtener flujo de entrada para leer objetos
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Persona dato = (Persona) inObjeto.readObject();
		System.out.println("Recibo: " + dato.getNombre() + " * " + dato.getEdad());

		// Modificación atributos del objeto
		dato.setNombre("Juan Ramos");
		dato.setEdad(44);

		// Envío del objeto mediante flujo de salida
		ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
		perSal.writeObject(dato);
		System.out.println("Envío: " + dato.getNombre() + " * " + dato.getEdad());

		// Cierre de los streams y sockets
		inObjeto.close();
		perSal.close();
		cliente.close();
	}
}