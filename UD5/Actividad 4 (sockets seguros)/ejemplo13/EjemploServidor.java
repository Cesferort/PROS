package ejemplo13;
import java.io.*;
import javax.net.ssl.*;

public class EjemploServidor 
{
	
	public static void main (String[] arg) throws IOException 
	{
		int puerto=6000;
		SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket servidorSSL= (SSLServerSocket) sfact.createServerSocket(puerto);
		SSLSocket clienteConectado = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream flujoSalida = null;
		
		for (int i =1; i < 5 ; i++) 
		{
			System.out.println("Esperando al cliente " + i);
			clienteConectado = (SSLSocket) servidorSSL.accept();
			flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
			
			// El servidor recibe un mensaje del cliente
			System.out.println("Recibiendo del cliente: " + i + "\n\t" + flujoEntrada.readUTF());
			flujoSalida = new DataOutputStream (clienteConectado.getOutputStream());
			
			// Saludo del servidor al cliente
			flujoSalida.writeUTF("Saludos del servidor al cliente");
		}
		
		// Cierre de streams y sockets
		flujoEntrada.close();
		flujoSalida.close();
		clienteConectado.close();
		servidorSSL.close();
	}
}