package ejemplo13;
import java.io.*;
import javax.net.ssl.*;

public class EjemploCliente 
{

	public static void main (String[] arg) throws IOException
	{
		String Host="localhost";
		int puerto=6000;
		System.out.println("PROGRAMA CLIENTE INICIADO....");
		SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket Cliente= (SSLSocket) sfact.createSocket(Host,puerto);
		
		// Creación del flujo de salida al servidor
		DataOutputStream flujoSalida = new DataOutputStream (Cliente.getOutputStream());
		
		// Envío de un saludo al servidor
		flujoSalida.writeUTF("Saludos al servidor desde el cliente");
		
		// Creación del flujo de entrada al servidor
		DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
		
		// El servidor me envia un mensaje
		System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
		
		// Cierre de streams y sockets
		flujoEntrada.close();
		flujoSalida.close();
		Cliente.close();
	}
}